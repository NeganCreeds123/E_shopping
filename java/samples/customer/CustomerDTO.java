/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.customer;

/**
 *
 * @author Dell
 */
public class CustomerDTO {
    private String Id;
    private String name;
    private String phoneNum;
    private String userId;
private String Email;

    public String getEmail() {
        return Email;
    }

    public CustomerDTO(String Id, String name, String phoneNum, String userId, String Email) {
        this.Id = Id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.userId = userId;
        this.Email = Email;
    }

    public CustomerDTO(String Id, String name, String phoneNum, String userId) {
        this.Id = Id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.userId = userId;
    }

    public CustomerDTO() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
}
