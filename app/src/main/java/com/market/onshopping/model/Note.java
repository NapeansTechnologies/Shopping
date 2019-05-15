package com.market.onshopping.model;
/**
 * Created by ravi on 20/02/18.
 */

public class Note {
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ITEMCOUNT = "itemCount";
    public static final String COLUMN_CHILD = "child";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DISCOUNT = "adult_amount";
    public static final String COLUMN_CHILD_AMOUNT = "child_amount";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DATE = "datee";

    private int id;
    private String note;
    private String timestamp, title, image, itemCount, child, amount,adult_amount,child_amount,datee;



    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT," + COLUMN_IMAGE + " TEXT," + COLUMN_TITLE + " TEXT," + COLUMN_ITEMCOUNT + " TEXT," + COLUMN_CHILD + " TEXT,"
                    + COLUMN_AMOUNT + " TEXT," + COLUMN_DISCOUNT + " TEXT," + COLUMN_CHILD_AMOUNT + " TEXT,"+ COLUMN_DATE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Note() {
    }



    public Note(int id, String note, String image, String title, String itemCount,
                String child, String amount, String adultAmount, String ChildAmount, String date, String timestamp) {
        this.id = id;
        this.note = note;
        this.image = image;
        this.title = title;
        this.itemCount = itemCount;
        this.child = child;
        this.amount = amount;
        this.adult_amount=adultAmount;
        this.child_amount=ChildAmount;
        this.timestamp = timestamp;
        this.datee = date;

    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAdult_amount() {
        return adult_amount;
    }

    public void setAdult_amount(String adult_amount) {
        this.adult_amount = adult_amount;
    }

    public String getChild_amount() {
        return child_amount;
    }

    public void setChild_amount(String child_amount) {
        this.child_amount = child_amount;
    }
    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }
}
