package com.market.onshopping.model;

public class itemforcategory {

    String itemID;
    String ItemName;
    String ItemDescription ;
    String ItemPrice ;
    String ItemIcon ;
    String Image1 ;
    String image2 ;
    String Image3;
    String Discount;
    String MinQuantityValue ;
    String Scale;
    Integer AvailableQuantity ;
    Boolean DeliveryAvailable ;
    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }


    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getItemIcon() {
        return ItemIcon;
    }

    public void setItemIcon(String itemIcon) {
        ItemIcon = itemIcon;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String image3) {
        Image3 = image3;
    }

    public String getMinQuantityValue() {
        return MinQuantityValue;
    }

    public void setMinQuantityValue(String minQuantityValue) {
        MinQuantityValue = minQuantityValue;
    }

    public Integer getAvailableQuantity() {
        return AvailableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

    public Boolean getDeliveryAvailable() {
        return DeliveryAvailable;
    }

    public void setDeliveryAvailable(boolean deliveryAvailable) {
        DeliveryAvailable = deliveryAvailable;
    }

    public String getItemID() {

        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }


    public int getRupess() {
        return 0;
    }
}

