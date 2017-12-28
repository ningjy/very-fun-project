/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Derian
 */
@Entity(name="CompositeItem")
public class CompositeItemEntity implements Serializable {
    
    private ArrayList<ItemEntity> items;
    private String name;
    private String description;
    private Long quantity;
    private Long rebundleLevel;
    @Id
    private String SKU;
    private String imgLink;
    private boolean active;
    private Double sellingPrice;
    
    public ArrayList<ItemEntity> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemEntity> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getRebundleLevel() {
        return rebundleLevel;
    }

    public void setRebundleLevel(Long rebundleLevel) {
        this.rebundleLevel = rebundleLevel;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    
    public String getSKU() {
        return SKU;
    }

    public void setSKU(String id) {
        this.SKU = SKU;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (SKU != null ? SKU.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompositeItemEntity)) {
            return false;
        }
        CompositeItemEntity other = (CompositeItemEntity) object;
        if ((this.SKU == null && other.SKU != null) || (this.SKU != null && !this.SKU.equals(other.SKU))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CompositeItemEntity[ id=" + SKU + " ]";
    }
    
}
