/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.order;

import samples.product.ProductDTO;

/**
 *
 * @author Dell
 */
public class OrderDetailDTO {
    private int orderId;
    private int productId;
    private int quantity;
    private ProductDTO product;

    public OrderDetailDTO(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderDetailDTO(int orderId,int productId,int quantity, ProductDTO product) {
        this.orderId = orderId;
      this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public OrderDetailDTO() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
    
    
}
