/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.order;

import java.sql.Date;

/**
 *
 * @author Dell
 */
public class OrderDTO {

    private int Id;
    private String CusId;
    private Date date;
    private double total;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

    public OrderDTO(int Id, String CusId, Date date, double total, String name) {
        this.Id = Id;
        this.CusId = CusId;
        this.date = date;
        this.total = total;
        this.name = name;
    }

    public OrderDTO(int Id, String CusId, Date date, double total) {
        this.Id = Id;
        this.CusId = CusId;
        this.date = date;
        this.total = total;
    }

    public OrderDTO() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCusId() {
        return CusId;
    }

    public void setCusId(String CusId) {
        this.CusId = CusId;
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
