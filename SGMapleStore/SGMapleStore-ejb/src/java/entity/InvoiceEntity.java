/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Derian
 */
@Entity(name="Invoice")
public class InvoiceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceNum;
    private String customerNotes;
    private LocalDateTime dateTime;
    private String contactUsername;
    private String billingAddress;
    private String shippingAddress;
    private Long contactNum;
    private String paymentReferenceNum;
    private String paymentMode;
    private Double discountAmt;
    private Double shippingAmt; 
    private List<Vector> itemList;
    private String status;
    private boolean active; 

    public Long getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(Long invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public String getContactUsername() {
        return contactUsername;
    }

    public void setContactUsername(String contactUsername) {
        this.contactUsername = contactUsername;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Long getContactNum() {
        return contactNum;
    }

    public void setContactNum(Long contactNum) {
        this.contactNum = contactNum;
    }

    public String getPaymentReferenceNum() {
        return paymentReferenceNum;
    }

    public void setPaymentReferenceNum(String paymentReferenceNum) {
        this.paymentReferenceNum = paymentReferenceNum;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
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

    public List<Vector> getItemList() {
        return itemList;
    }

    public void setItemList(List<Vector> itemList) {
        this.itemList = itemList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
     public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceNum != null ? invoiceNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceEntity)) {
            return false;
        }
        InvoiceEntity other = (InvoiceEntity) object;
        if ((this.invoiceNum == null && other.invoiceNum != null) || (this.invoiceNum != null && !this.invoiceNum.equals(other.invoiceNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.InvoiceEntity[ id=" + invoiceNum + " ]";
    }
}
