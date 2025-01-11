/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.product;

/**
 *
 * @author 84878
 */
public class ProductDTO {
    private int id;
    private int categoryID;
    private String name;
    private String image;
    private String status;
    private double price;
    //private int quantity;
//
//    public ProductDTO(int id, int categoryID, String name, String image, String status, double price, int quantity) {
//        this.id = id;
//        this.categoryID = categoryID;
//        this.name = name;
//        this.image = image;
//        this.status = status;
//        this.price = price;
//        this.quantity = quantity;
//    }

    public ProductDTO() {
    }

    public ProductDTO(int id, int categoryID, String name, String image, String status, double price) {
        this.id = id;
        this.categoryID = categoryID;
        this.name = name;
        this.image = image;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }

//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
   
}
//a