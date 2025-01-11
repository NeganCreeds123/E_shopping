/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.order;

/**
 *
 * @author ASUS
 */
public class PieChartDTO {

    private String name;
    private int quantity;

    public PieChartDTO() {
    }

    public PieChartDTO(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
