package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Invoice")
public class InvoiceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceNum;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @OneToOne(mappedBy = "invoice")
    private SalesOrderEntity salesOrder;
    private Long transactionNumber;

    public SalesOrderEntity getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrderEntity salesOrder) {
        this.salesOrder = salesOrder;
    }
    
    public Long getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(Long invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDate(Date dateTime) {
        this.dateTime = dateTime;
    }
}