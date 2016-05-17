package com.sourcey.materiallogindemo.Models;


import org.json.JSONObject;

public class User extends Model{

    private int id;
    private String name;
    private String email;
    private String password;

    public JSONObject convertToJson(){
        return createJson(this);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
