package com.leecottrell.mysqldemo.Customer;

public class Customer {
    private int custID;
    private String fname;
    private String lname;
    private String payDetails;


    public Customer() {
    }

    public Customer(int custID, String fname, String lname, String payDetails) {
        this.custID = custID;
        this.fname = fname;
        this.lname = lname;
        this.payDetails = payDetails;
    }

    public int getCustID() {
        return this.custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPayDetails() {
        return this.payDetails;
    }

    public void setPayDetails(String payDetails) {
        this.payDetails = payDetails;
    }

}
