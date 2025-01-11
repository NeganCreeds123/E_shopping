/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.util.Date;

public class TransactionDTO {
    private String transactionId;
    private String orderId;
    private String type;
    private double amount;
    private String walletId;
    private Date date;

    public TransactionDTO() {
    }

    public TransactionDTO(String transactionId, String orderId, String type, double amount, String walletId, Date date) {
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.type = type;
        this.amount = amount;
        this.walletId = walletId;
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWalletId() {
        return walletId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}

