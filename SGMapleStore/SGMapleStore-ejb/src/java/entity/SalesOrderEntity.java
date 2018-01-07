/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Derian
 */
@Entity(name = "SalesOrder")
public class SalesOrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long salesOrderNumber;
    private String deliveryNotes;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @OneToOne
    private ContactEntity customer;
    private String creditCardNum;
    @OneToOne
    private InvoiceEntity invoice;
    private Double discountAmt;
    private Double shippingAmt;
    private Double totalPrice;
    @OneToMany(mappedBy = "salesOrder")
    private Collection<ItemEntity> itemList = new ArrayList<ItemEntity>();
    private String status;
    private boolean isPaid;

    public SalesOrderEntity(){
        this.creationDateTime=new Date();
        this.isPaid=false;
    }
    
    public SalesOrderEntity(String deliveryNotes, ContactEntity customer, String creditCardNum, InvoiceEntity invoice, Double discountAmt, Double shippingAmt, String status) {       
        this.deliveryNotes = deliveryNotes;
        this.creationDateTime = new Date();
        this.customer = customer;
        this.creditCardNum = creditCardNum;
        this.invoice = invoice;
        this.discountAmt = discountAmt;
        this.shippingAmt = shippingAmt;
        this.status = status;
        this.isPaid = false;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public ContactEntity getCustomer() {
        return customer;
    }

    public void setCustomer(ContactEntity customer) {
        this.customer = customer;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public Double getShippingAmt() {
        return shippingAmt;
    }

    public void setShippingAmt(Double shippingAmt) {
        this.shippingAmt = shippingAmt;
    }

    public Collection<ItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(Collection<ItemEntity> itemList) {
        this.itemList = itemList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
            
    public Long getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(Long salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salesOrderNumber != null ? salesOrderNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesOrderEntity)) {
            return false;
        }
        SalesOrderEntity other = (SalesOrderEntity) object;
        if ((this.salesOrderNumber == null && other.salesOrderNumber != null) || (this.salesOrderNumber != null && !this.salesOrderNumber.equals(other.salesOrderNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SalesOrder[ id=" + salesOrderNumber + " ]";
    }
    
}
