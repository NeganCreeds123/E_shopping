/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.order;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class OrderGraphDTO {

    private Date date;
    private double total;

    public OrderGraphDTO() {
    }

    public OrderGraphDTO(Date date, double total) {
        this.date = date;
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
