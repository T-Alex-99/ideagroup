package com.example.ideaapp.model;

public class Room {
    private long id;
    private String kind;
    private String number;

    public Room() {
    }

    public Room(int id, String kind, String number) {
       this.id = id;
       this.kind = kind;
       this.number = number;
     }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKind() {
       return kind;
    }

    public void setKind(String kind) {
       this.kind = kind;
    }

    public String getNumber() {
       return number;
    }

    public void setNumber(String number) {
       this.number = number;
    }

    @Override
    public String toString() {
       return "RoomObject [id=" + id + ", kind=" + kind + ", number=" + number + "]";
    }
}
