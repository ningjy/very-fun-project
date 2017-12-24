/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ContactEntity customer;
    private Long salesOrderID; //to replace with sales order entity
    private LocalDate date;
    private String terms;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    private String priceList; //to replace with PriceList entity
    private String warehouse; //to replace with warehouse entity
    private EmployeeEntity salesperson;
    private String products; // to replace with ArrayList<ProductEntity>
    private Long totalAmount;
    
    public InvoiceEntity(){}
    
    public InvoiceEntity(Long id, ContactEntity customer, Long salesOrderID, LocalDate date, String terms, String priceList, String warehouse, EmployeeEntity salesperson, String products, Long totalAmount) {
        this.id = id;
        this.customer = customer;
        this.salesOrderID = salesOrderID;
        this.date = date;
        this.terms = terms;
        this.priceList = priceList;
        this.warehouse = warehouse;
        this.salesperson = salesperson;
        this.products = products;
        this.totalAmount = totalAmount;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceEntity)) {
            return false;
        }
        InvoiceEntity other = (InvoiceEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.InvoiceEntity[ id=" + id + " ]";
    }

    public ContactEntity getCustomer() {
        return customer;
    }

    public void setCustomer(ContactEntity customer) {
        this.customer = customer;
    }

    public Long getSalesOrderID() {
        return salesOrderID;
    }

    public void setSalesOrderID(Long salesOrderID) {
        this.salesOrderID = salesOrderID;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public EmployeeEntity getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(EmployeeEntity salesperson) {
        this.salesperson = salesperson;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
    
}
