package com.example.crudapp.database;

public class Note {
    String fName;
    String email;

    public Note(String fName, String email){
        this.fName=fName;
        this.email=email;
    }
    public String getName(){
        return fName;
    }
    public void setName(String fName){
        this.fName=fName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
}
