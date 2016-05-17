package com.sourcey.materiallogindemo.Models;


import org.json.JSONObject;

public class Restaurant extends Model{

    private int id;
    private String name;
    private String detail;
    private String image;

    public JSONObject convertToJson(){
        return createJson(this);
    }

    public static Restaurant createFromJson(JSONObject object) {
        return createFromJson(object, Restaurant.class);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getImage() {
        return image;
    }
}
