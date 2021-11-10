package com.leecottrell.lecture1.Rooms;

public class Rooms {
    private int roomNum;
    private String guest;

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public Rooms(int roomNum, String guest) {
        this.roomNum = roomNum;
        this.guest = guest;
    }

    public Rooms() {
    }

    
}
