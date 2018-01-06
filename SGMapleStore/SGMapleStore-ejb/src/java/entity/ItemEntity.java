package entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Item")
public class ItemEntity implements Serializable {
    @Id
    private String itemSKU;
    private String itemName;
    private String itemDescription;
    private Double itemQuantity;
    private Double itemReorderLevel;
    private Double itemSellingPrice;
    private String itemImageDirPath;
    private String vendorID;
    private String vendorProductCode;
    private boolean activeStatus;
    private ArrayList<String> subCategories;
    
    public ArrayList<String> getSubCategories() { return subCategories; }
    public void setSubCategories(ArrayList<String> subCategories) { this.subCategories = subCategories; }
    
    public String getItemSKU() { return itemSKU; }
    public String getItemName() { return itemName; }
    public String getItemDescription() { return itemDescription; }
    public Double getItemQuantity() { return itemQuantity; }
    public Double getItemReorderLevel() { return itemReorderLevel; }
    public Double getItemSellingPrice() { return itemSellingPrice; }
    public String getItemImageDirPath() { return itemImageDirPath; }
    public String getVendorID() { return vendorID; }
    public String getVendorProductCode() { return vendorProductCode; }
    public boolean getActiveStatus() { return activeStatus; }
    
    public void setItemSKU(String itemSKU) { this.itemSKU = itemSKU; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }
    public void setItemQuantity(Double itemQuantity) { this.itemQuantity = itemQuantity; }
    public void setItemReorderLevel(Double itemReorderLevel) { this.itemReorderLevel = itemReorderLevel; }
    public void setItemSellingPrice(Double itemSellingPrice) { this.itemSellingPrice = itemSellingPrice; }
    public void setItemImageDirPath(String itemImageDirPath) { this.itemImageDirPath = itemImageDirPath; }
    public void setVendorID(String vendorID) { this.vendorID = vendorID; }
    public void setVendorProductCode(String vendorProductCode) { this.vendorProductCode = vendorProductCode; }
    public void setActiveStatus(boolean activeStatus) { this.activeStatus = activeStatus; }
}