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
import entity.CategoryEntity;
import entity.SalesOrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

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
    public boolean createContact(String contactSalutation, String contactFirstName, String contactLastName, String contactEmail, 
            String contactPhone, String contactType, String contactBillingAttn, String contactBillingAddress, String contactBillingCity, 
            String contactBillingState, String contactBillingZipCode, String contactBillingCountry, String contactBillingFax, 
            String contactBillingPhone, String contactShippingAttn, String contactShippingAddress, String contactShippingCity, 
            String contactShippingState, String contactShippingZipCode, String contactShippingCountry, String contactShippingFax, 
            String contactShippingPhone, String contactUsername, String contactPassword, String suppCompanyName, String suppBillAccNo, 
            String contactNotes) {
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(contactPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }
        
        cEntity = new ContactEntity();
        cEntity.createContact(contactSalutation, contactFirstName, contactLastName, contactEmail, contactPhone, contactType, 
                contactBillingAttn, contactBillingAddress, contactBillingCity, contactBillingState, contactBillingZipCode, 
                contactBillingCountry, contactBillingFax, contactBillingPhone, contactShippingAttn, contactShippingAddress, 
                contactShippingCity, contactShippingState, contactShippingZipCode, contactShippingCountry, contactShippingFax, 
                contactShippingPhone, contactUsername, hashedPassword, suppCompanyName, suppBillAccNo, contactNotes);
        em.persist(cEntity);
        return true;
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
            contactVec.add(contactE.getSuppCompanyName());
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
            contactInfoVec.add(cEntity.getContactEmail());
            contactInfoVec.add(df.format(cEntity.getContactCreationDate()));
            
            return contactInfoVec;
        }
        return null;
    }
    
    @Override
    public boolean deleteMultipleContact(String[] contactEmailListArr) {
        boolean contactDeletionStatus = true;
        for (String contactEmail : contactEmailListArr) {
            if (lookupContact(contactEmail) == null) {
                contactDeletionStatus = false;
            } else {
                cEntity = lookupContact(contactEmail);
                em.remove(cEntity);
                em.flush();
                em.clear();
            }
        }
        return contactDeletionStatus;
    }
    
    @Override
    public boolean deleteAContact(String hiddenContactEmail) {
        boolean contactDeletionStatus = true;
        if (lookupContact(hiddenContactEmail) == null) {
            contactDeletionStatus = false;
        } else {
            cEntity = lookupContact(hiddenContactEmail);
            em.remove(cEntity);
            em.flush();
            em.clear();
        }
        return contactDeletionStatus;
    }
    
    @Override
    public boolean createEmployee(String empSalutation, String empFirstName, String empLastName, String empEmail, 
            String empPhone, String empUniqueIdentifier, String empDateOfBirth, String empGender, String empRace, 
            String empNationality, String empResidentAddress, String empResidentCity, String empResidentState, 
            String empResidentZipCode, String empResidentCountry, String empJobDepartment, String empJobDesignation, 
            String empUsername, String empPassword, String empNotes) {
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(empPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }
        
        eEntity = new EmployeeEntity();
        eEntity.createEmployee(empSalutation, empFirstName, empLastName, empEmail, empPhone, empUniqueIdentifier, empDateOfBirth, 
                empGender, empRace, empNationality, empResidentAddress, empResidentCity, empResidentState, empResidentZipCode, 
                empResidentCountry, empJobDepartment, empJobDesignation, empUsername, hashedPassword, empNotes);
        em.persist(eEntity);
        return true;
    }
    
    @Override
    public List<Vector> viewEmployeeList(){
        Query q = em.createQuery("SELECT e FROM Employee e");
        List<Vector> employeeList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            EmployeeEntity employeeE = (EmployeeEntity) o;
            Vector employeeVec = new Vector();
            
            employeeVec.add(employeeE.getEmpFirstName());
            employeeVec.add(employeeE.getEmpLastName());
            employeeVec.add(employeeE.getEmpEmail());
            employeeVec.add(employeeE.getEmpPhone());
            employeeVec.add(employeeE.getEmpJobDepartment());
            employeeVec.add(employeeE.getEmpJobDesignation());
            employeeList.add(employeeVec);
        }
        return employeeList;
    }
    
    @Override
    public Vector getEmployeeInfo(String employeeIdentifier) {
        eEntity = lookupEmployee(employeeIdentifier);
        Vector employeeInfoVec = new Vector();
        
        if (eEntity != null) {
            DateFormat df = new SimpleDateFormat("dd MMMMM yyyy");
            
            employeeInfoVec.add(eEntity.getEmpFirstName());
            employeeInfoVec.add(eEntity.getEmpLastName());
            employeeInfoVec.add(eEntity.getEmpEmail());
            employeeInfoVec.add(df.format(eEntity.getEmpCreationDate()));
            
            return employeeInfoVec;
        }
        return null;
    }
    
    @Override
    public boolean deleteMultipleEmployee(String[] empEmailListArr) {
        boolean empDeletionStatus = true;
        for (String empEmail : empEmailListArr) {
            if (lookupEmployee(empEmail) == null) {
                empDeletionStatus = false;
            } else {
                eEntity = lookupEmployee(empEmail);
                em.remove(eEntity);
                em.flush();
                em.clear();
            }
        }
        return empDeletionStatus;
    }
    
    @Override
    public boolean deleteAnEmployee(String hiddenEmpEmail) {
        boolean empDeletionStatus = true;
        if (lookupEmployee(hiddenEmpEmail) == null) {
            empDeletionStatus = false;
        } else {
            eEntity = lookupEmployee(hiddenEmpEmail);
            em.remove(eEntity);
            em.flush();
            em.clear();
        }
        return empDeletionStatus;
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
    
     public boolean createInventoryCategory(String newCategoryName,String newCategoryDesc,ArrayList<String> sCats){
        CategoryEntity newInventoryCategory = new CategoryEntity();
        newInventoryCategory.setName(newCategoryName);
        newInventoryCategory.setDescription(newCategoryDesc);
        newInventoryCategory.setSubcategories(sCats);
        newInventoryCategory.setActive(true);
        em.persist(newInventoryCategory);
        return true;
    }
    
    
    public List<Vector> viewAllInventoryCategories(){
        Query q = em.createQuery("SELECT c FROM Category c");
        List results = q.getResultList();
        List<Vector> categories = new ArrayList<Vector>();
        
        for(Object o:q.getResultList()){
            CategoryEntity cate = (CategoryEntity) o;
            Vector cateVec = new Vector();
            cateVec.add(cate.getName());
            cateVec.add(cate.getDescription());
            ArrayList<String> subs = cate.getSubcategories();
            String subsline = "";
            for(int n=0;n<subs.size();n++){
                if(n==0){
                    subsline = subsline+subs.get(n);
                }else{
                    subsline = subsline+";"+subs.get(n);
                }
            }
            cateVec.add(subsline);
            categories.add(cateVec);
        }
        return categories;
    }
    
    public void modifyInventoryCategory(String categoryName,String updatedCategoryDesc,ArrayList<String> sCats){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.name=:cateName");
        q.setParameter("cateName", categoryName);
        CategoryEntity categoryE = (CategoryEntity)q.getSingleResult();
        categoryE.setDescription(updatedCategoryDesc);
        categoryE.setSubcategories(sCats);
        em.persist(categoryE);
    }
    
    @Override
    public boolean createCompositeItem(String compositeName, String compositeSKU, String compositeSellPrice, 
            String compositeRebundleLvl, String compositeDescription, String fileName, String[] itemNameArr, String[] itemSKUArr, 
            String[] itemQtyRequiredArr) {
        ciEntity = new CompositeItemEntity();
        
        List<Vector> packageItemList = new ArrayList<Vector>();
        for(int i = 0; i < itemNameArr.length; i++) {
            Vector packageItemVec = new Vector();
            packageItemVec.add(itemNameArr[i]);
            packageItemVec.add(itemSKUArr[i]);
            packageItemVec.add(itemQtyRequiredArr[i]);
            packageItemList.add(packageItemVec);
        }
        ciEntity.createCompositeItem(compositeName, compositeSKU, Double.parseDouble(compositeSellPrice), 
                Double.parseDouble(compositeRebundleLvl), compositeDescription, fileName, packageItemList);
        em.persist(ciEntity);
        return true;
    }
    
    @Override
    public List<Vector> viewCompositeItemList(){
        Query q = em.createQuery("SELECT c FROM CompositeItem c");
        List<Vector> compItemList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            CompositeItemEntity compE = (CompositeItemEntity) o;
            Vector compVec = new Vector();
            
            compVec.add(compE.getCompositeImagePath());
            compVec.add(compE.getCompositeName());
            compVec.add(compE.getCompositeSKU());
            compVec.add(compE.getCompositeQuantity());
            compVec.add(compE.getCompositeSellPrice());
            compVec.add(compE.getCompositeRebundleLvl());
            compItemList.add(compVec);
        }
        return compItemList;
    }
    
    @Override
    public List<Vector> viewSalesOrderlist(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Query q = em.createQuery("SELECT s FROM SalesOrder s");
        List<Vector> salesOrderList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            SalesOrderEntity salesOrder = (SalesOrderEntity) o;
            ContactEntity cust = salesOrder.getCustomer();
            Vector soVec = new Vector();
            /*
            Order of columns in view page:
            ID, creation date, status, full name, username, total amount NETT, 
            */
            soVec.add(salesOrder.getSalesOrderNumber());
            soVec.add(df.format(salesOrder.getCreationDateTime()));
            soVec.add(salesOrder.getStatus());
            soVec.add(cust.getContactSalutation()+" "+cust.getContactFirstName()+" "+cust.getContactLastName());
            soVec.add(cust.getContactUsername());           
            soVec.add(salesOrder.getTotalPrice()+salesOrder.getShippingAmt()-salesOrder.getDiscountAmt());
            salesOrderList.add(soVec);
        }
        return salesOrderList;
    }
    
    @Override
    public List<Vector> viewInvoiceList() {//WORK IN PROGRESS
        List<Vector> invoiceList = new ArrayList<Vector>();
        /*SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ContactEntity customer = null;
        //Extract invoices
        Query q = em.createQuery("SELECT i FROM Invoice i");
        for(Object o: q.getResultList()){
            InvoiceEntity invoiceE = (InvoiceEntity) o;
            Vector invoiceVec = new Vector();
            
            //Extract the customer based on the stored username
            /*Query q1 = em.createQuery("SELECT c FROM Contact c WHERE c.contactUsername =: username");
            q1.setParameter("username",invoiceE.getContactUsername());
            invoiceVec.add(invoiceE.getInvoiceNum());
            List result = q1.getResultList();
            if(!result.isEmpty()){
                Iterator it = result.iterator();
                customer = (ContactEntity)it.next();
            }*/
            /*invoiceVec.add(invoiceE.getInvoiceNum());
            //invoiceVec.add(df.format(invoiceE.getDateTime()));
            invoiceVec.add(invoiceE.getPaymentReferenceNum());
            invoiceVec.add(invoiceE.getContactUsername());
            /*invoiceVec.add(customer.getContactSalutation()
                    +" "+customer.getContactFirstName()
                    +" "+customer.getContactLastName());*/    
            /*invoiceVec.add(invoiceE.getShippingAmt()+invoiceE.getDiscountAmt());

            invoiceList.add(invoiceVec);
        }*/
        return invoiceList;
    }

    /* MISCELLANEOUS METHOD HELPERS */
    @Override
    public boolean empLogin(String empUsername, String empPassword) {
        /* Must perform hashing here, not on the servlet side. Otherwise will produce different hash values */
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(empPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }

        eEntity = new EmployeeEntity();
        try{
            Query q = em.createQuery("SELECT e FROM Employee e WHERE e.empUsername = :empUsername");
            q.setParameter("empUsername", empUsername);
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
        // if(eEntity.getEmpPassword().equals(hashedPassword)) { return true; }
        if(eEntity.getEmpPassword().equals(empPassword)) { return true; }
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
    
    public EmployeeEntity lookupEmployee(String emailAddress){
        EmployeeEntity ee = new EmployeeEntity();
        try{
            Query q = em.createQuery("SELECT e FROM Employee e WHERE e.empEmail = :emailAddress");
            q.setParameter("emailAddress", emailAddress);
            ee = (EmployeeEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Employee cannot be found. " + enfe.getMessage());
            em.remove(ee);
            ee = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Employee does not exist. " + nre.getMessage());
            em.remove(ee);
            ee = null;
        }
        return ee;
    }
    
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