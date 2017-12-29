/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Derian
 */
@Entity(name="Bill")
public class BillEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billNum;
    private Long poNum;
    private LocalDateTime creationDateTime;
    private LocalDateTime dueDateTime;
    private String paymentReferenceNum;
    private Double discountFactor;
    private LocalDateTime discountDateTime;
    private String paymentMode;
    private String status;
    private LocalDateTime paymentDateTime;
    private boolean active;

    public Long getPoNum() {
        return poNum;
    }

    public void setPoNum(Long poNum) {
        this.poNum = poNum;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public String getPaymentReferenceNum() {
        return paymentReferenceNum;
    }

    public void setPaymentReferenceNum(String paymentReferenceNum) {
        this.paymentReferenceNum = paymentReferenceNum;
    }

    public Double getDiscountFactor() {
        return discountFactor;
    }

    public void setDiscountFactor(Double discountFactor) {
        this.discountFactor = discountFactor;
    }

    public LocalDateTime getDiscountDateTime() {
        return discountDateTime;
    }

    public void setDiscountDateTime(LocalDateTime discountDateTime) {
        this.discountDateTime = discountDateTime;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(LocalDateTime paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getBillNum() {
        return billNum;
    }

    public void setBillNum(Long billNum) {
        this.billNum = billNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billNum != null ? billNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillEntity)) {
            return false;
        }
        BillEntity other = (BillEntity) object;
        if ((this.billNum == null && other.billNum != null) || (this.billNum != null && !this.billNum.equals(other.billNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BillEntity[ id=" + billNum + " ]";
    }
    
}
