package com.example.harish.moviestage2;

public class TrailerLinks {
    String trailer_id;
    String key_trailer;
    String name;
    String type;

    public TrailerLinks(String trailer_id, String key_trailer, String name, String type) {
        this.trailer_id = trailer_id;
        this.key_trailer = key_trailer;
        this.name = name;
        this.type = type;
    }

    public String getTrailer_id() {
        return trailer_id;
    }

    public void setTrailer_id(String trailer_id) {
        this.trailer_id = trailer_id;
    }

    public String getKey_trailer() {
        return key_trailer;
    }

    public void setKey_trailer(String key_trailer) {
        this.key_trailer = key_trailer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
