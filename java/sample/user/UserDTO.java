/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author Dell
 */
public class UserDTO {

    private String userId;
    private String email;
    private String password;
    private String role;
    private String status;

    public UserDTO() {
        this.userId = "";
        this.email = "";
          this.password = "";
        this.role = "";
         this.status = "";
      
    }

    public UserDTO(String userId, String email, String password, String role, String status) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public UserDTO(String userId, String email, String password, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUserID() {
        return userId;
    }

    public void setUserID(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
