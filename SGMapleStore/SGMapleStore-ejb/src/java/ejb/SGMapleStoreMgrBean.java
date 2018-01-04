package ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.EmployeeEntity;
import entity.ContactEntity;
import entity.ItemEntity;
import entity.InventoryLogEntity;
import entity.CompositeItemEntity;
import entity.InvoiceEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Stateless
public class SGMapleStoreMgrBean implements CommonInfrastructureRemote, WarehouseTransportRemote {
    @PersistenceContext
    private EntityManager em;
    
    private EmployeeEntity eEntity;
    private ContactEntity cEntity;
    private InventoryLogEntity ilEntity;
    private CompositeItemEntity ciEntity;
    private InvoiceEntity invoice;
    
    @Override
    public void createContact(String contactSalutation, String contactFirstName, String contactLastName, String contactEmail, 
            String contactPhone, String contactType, String contactBillingAttn, String contactBillingAddress, String contactBillingCity, 
            String contactBillingState, String contactBillingZipCode, String contactBillingCountry, String contactBillingFax, 
            String contactBillingPhone, String contactShippingAttn, String contactShippingAddress, String contactShippingCity, 
            String contactShippingState, String contactShippingZipCode, String contactShippingCountry, String contactShippingFax, 
            String contactShippingPhone, String contactUsername, String contactPassword, String contactNotes) {
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(contactPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }
        
        cEntity = new ContactEntity();
        cEntity.createContact(contactSalutation, contactFirstName, contactLastName, contactEmail, contactPhone, contactType, 
                contactBillingAttn, contactBillingAddress, contactBillingCity, contactBillingState, contactBillingZipCode, 
                contactBillingCountry, contactBillingFax, contactBillingPhone, contactShippingAttn, contactShippingAddress, 
                contactShippingCity, contactShippingState, contactShippingZipCode, contactShippingCountry, contactShippingFax, 
                contactShippingPhone, contactUsername, hashedPassword, contactNotes);
        em.persist(cEntity);
    }
    
    @Override
    public List<Vector> viewContactList(){
        Query q = em.createQuery("SELECT c FROM Contact c");
        List<Vector> contactList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            ContactEntity contactE = (ContactEntity) o;
            Vector contactVec = new Vector();
            
            contactVec.add(contactE.getContactSalutation());
            contactVec.add(contactE.getContactFirstName());
            contactVec.add(contactE.getContactLastName());
            contactVec.add(contactE.getContactEmail());
            contactVec.add(contactE.getContactPhone());
            contactVec.add(contactE.getContactType());
            contactVec.add(contactE.getContactBillingCity());
            contactVec.add(contactE.getContactBillingCountry());
            contactList.add(contactVec);
        }
        return contactList;
    }
    
    @Override
    public Vector getContactInfo(String contactIdentifier) {
        cEntity = lookupContact(contactIdentifier);
        Vector contactInfoVec = new Vector();
        
        if (cEntity != null) {
            DateFormat df = new SimpleDateFormat("dd MMMMM yyyy");
            
            contactInfoVec.add(cEntity.getContactFirstName());
            contactInfoVec.add(cEntity.getContactLastName());
            contactInfoVec.add(df.format(cEntity.getContactCreationDate()));
            
            return contactInfoVec;
        }
        return null;
    }
    
    @Override
    public boolean empLogin(String empNRIC, String empPassword) {
        /* Must perform hashing here, not on the servlet side. Otherwise will produce different hash values */
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(empPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }

        eEntity = new EmployeeEntity();
        try{
            Query q = em.createQuery("SELECT e FROM Employee e WHERE e.empNRIC = :empNRIC");
            q.setParameter("empNRIC", empNRIC);
            eEntity = (EmployeeEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Employee cannot be found. " + enfe.getMessage());
            em.remove(eEntity);
            eEntity = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Employee does not exist. " + nre.getMessage());
            em.remove(eEntity);
            eEntity = null;
        }
        
        if(eEntity == null) { return false; }
        //if(eEntity.getEmpPassword().equals(hashedPassword)) { return true; }
        if(eEntity.getEmpPassword().equals(empPassword)) { return true; }    // TO BE REVERTED
        return false;
    }
    
    public ContactEntity lookupContact(String emailAddress){
        ContactEntity ce = new ContactEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Contact c WHERE c.contactEmail = :emailAddress");
            q.setParameter("emailAddress", emailAddress);
            ce = (ContactEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Contact cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Contact does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    /* WAREHOUSE-TRANSPORT MODULE (JSON) */
    @Override
    public List<Vector> getItemListingNames() {
        Query q = em.createQuery("SELECT i FROM Item i");
        
        List<Vector> itemList = new ArrayList();
        for(Object o: q.getResultList()){
            ItemEntity ie = (ItemEntity) o;
            Vector itemVec = new Vector();
            
            itemVec.add(ie.getItemName());
            itemVec.add(ie.getItemSKU());
            itemVec.add(ie.getItemQuantity());
            itemVec.add(ie.getItemSellingPrice());
            itemList.add(itemVec);
        }
        return itemList;
    }

    @Override
    public boolean createInventoryLog(String userNRIC, String logDate, String logReason, String logDescription, 
            String[] itemNameArr, String[] itemSKUArr, String[] itemQtyArr, String[] itemQtyAdjustArr) {
        boolean logCreationStatus = true;
        Double itemQtyBeforeAdjust = 0.0;
        Double itemQtyAfterAdjust = 0.0;
        
        for(int i = 0; i < itemNameArr.length; i++) {
            ilEntity = new InventoryLogEntity();
            itemQtyBeforeAdjust = Double.parseDouble(itemQtyArr[i]);
            String qtyOperator = itemQtyAdjustArr[i].substring(0, 1);
            String qtyAdjustValue = itemQtyAdjustArr[i].substring(1, itemQtyAdjustArr[i].length());
            if(qtyOperator.equals("+")) {
                itemQtyAfterAdjust = itemQtyBeforeAdjust + Double.parseDouble(qtyAdjustValue);
                ilEntity.createInventoryLog(userNRIC, logDate, logReason, logDescription, itemNameArr[i], itemSKUArr[i], 
                    itemQtyBeforeAdjust, itemQtyAfterAdjust, itemQtyAdjustArr[i]);
                em.persist(ilEntity);
            }
            else if(qtyOperator.equals("-") && Double.parseDouble(qtyAdjustValue) > 0) {
                itemQtyAfterAdjust = itemQtyBeforeAdjust - Double.parseDouble(qtyAdjustValue);
                ilEntity.createInventoryLog(userNRIC, logDate, logReason, logDescription, itemNameArr[i], itemSKUArr[i], 
                    itemQtyBeforeAdjust, itemQtyAfterAdjust, itemQtyAdjustArr[i]);
                em.persist(ilEntity);
            }
            else {
                logCreationStatus = false;
            }
        }
        return logCreationStatus;
    }
    
    @Override
    public List<Vector> viewInventoryLogList(){
        Query q = em.createQuery("SELECT l FROM InventoryLog l");
        List<Vector> logList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            InventoryLogEntity logE = (InventoryLogEntity) o;
            Vector logVec = new Vector();
            
            logVec.add(logE.getLogDate());
            logVec.add(logE.getLogReason());
            logVec.add(logE.getLogCreatorID());
            logVec.add(logE.getItemName());
            logVec.add(logE.getItemSKU());
            logVec.add(logE.getItemQtyAdjustValue());
            logList.add(logVec);
        }
        return logList;
    }
    
    @Override
    public boolean createCompositeItem(String compositeName, String compositeSKU, String compositeSellPrice, 
            String compositeRebundleLvl, String compositeDescription, String[] itemNameArr, String[] itemSKUArr, 
            String[] itemQtyRequiredArr) {
        Double parseSellPrice = 0.0;
        Double parseRebundleLvl = 0.0;
        ciEntity = new CompositeItemEntity();
        
        /* === MUST USE NumberFormatException, ELSE WILL GET PARSING ERROR === */
        try { parseSellPrice = Double.parseDouble(compositeSellPrice); }
        catch (NumberFormatException ex){ ex.printStackTrace(); }
        try { parseRebundleLvl = Double.parseDouble(compositeRebundleLvl); }
        catch (NumberFormatException ex){ ex.printStackTrace(); }
        
        List<Vector> packageItemList = new ArrayList<Vector>();
        for(int i = 0; i < itemNameArr.length; i++) {
            Vector packageItemVec = new Vector();
            packageItemVec.add(itemNameArr[i]);
            packageItemVec.add(itemSKUArr[i]);
            packageItemVec.add(itemQtyRequiredArr[i]);
            packageItemList.add(packageItemVec);
        }
        ciEntity.createCompositeItem(compositeName, compositeSKU, parseSellPrice, parseRebundleLvl, 
                compositeDescription, packageItemList);
        em.persist(ciEntity);
        return true;
    }
    
    @Override
    public List<Vector> viewInvoiceList() {
        Query q = em.createQuery("SELECT i FROM Invoice i");
        List<Vector> invoiceList = new ArrayList<Vector>();
        
        /*for(Object o: q.getResultList()){
            InvoiceEntity invoiceE = (InvoiceEntity) o;
            Vector invoiceVec = new Vector();
            
            invoiceVec.add(invoiceE.getCustomer().getContactSalutation()
                    +" "+invoiceE.getCustomer().getContactFirstName()
                    +" "+invoiceE.getCustomer().getContactLastName());
            invoiceVec.add(invoiceE.getDate());
            invoiceVec.add(invoiceE.getId());
            invoiceVec.add(invoiceE.getSalesOrderID());
            invoiceVec.add(invoiceE.getTotalAmount());

            invoiceList.add(invoiceVec);
        }*/
        return invoiceList;
    }

    /* MISCELLANEOUS METHOD HELPERS */
    public String encodePassword(String password) throws NoSuchAlgorithmException {
        String hashedValue = "";
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            hashedValue = sb.toString();
        }      
        return hashedValue;
    }
    
}
