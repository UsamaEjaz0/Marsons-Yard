/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.salesReturn;

import javafx.scene.control.Button;

/**
 *
 * @author uejaz
 */
public class Items {
    String name, primaryItem, description, quantity, qtyReturned, unit, pricePerUnit, amount, qrAmount;
    
    public Items(  String name, String primaryItem, String description, String quantity, String unit, String pricePerUnit, String amount) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.amount = amount;
        this.primaryItem = primaryItem;
        
        
        
    }

    public String getQrAmount() {
        return qrAmount;
    }

    public void setQrAmount(String qrAmount) {
        this.qrAmount = qrAmount;
    }

    public Items(String name, String primaryItem, String description, String quantity, String qtyReturned, String unit, String pricePerUnit, String amount, String qrAmount) {
        this.name = name;
        this.primaryItem = primaryItem;
        this.description = description;
        this.quantity = quantity;
        this.qtyReturned = qtyReturned;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.amount = amount;
        this.qrAmount = qrAmount;
    }
    public Items(  String name, String primaryItem, String description, String quantity,String qtyReturned, String unit, String pricePerUnit, String amount) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.amount = amount;
        this.primaryItem = primaryItem;
        this.qtyReturned = qtyReturned;
        
        
        
    }

    public String getQtyReturned() {
        return qtyReturned;
    }

    public void setQtyReturned(String qtyReturned) {
        this.qtyReturned = qtyReturned;
    }

    

    public String getPrimaryItem() {
        return primaryItem;
    }

    public void setPrimaryItem(String primaryItem) {
        this.primaryItem = primaryItem;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

   
}
