package com.example.addharcard_barcode;
/**
 * @author onkar.chopade
 */

public class AadhaarCard 
{
    String uid;
    public String name;
    String gender;
    String yob;
    String co;
    String house;
    String lm;
    String loc;
    String vtc;
    String po;
    String dist;
    String subdist;
    String state;
    String pincode;
    String dob;
    String originalXML;

    String getFormattedUID() {
        if (uid.length() == 12) {
            return uid.substring(0, 4) + " " + uid.substring(4, 8) + " " + uid.substring(8, 12);
        }
        return uid;
    }

    String getAddress() {
        return house + ", " + lm + ", " + loc + ", " + dist + ", " + subdist + ", " + state + ".\nPincode: " + pincode;
    }
}
