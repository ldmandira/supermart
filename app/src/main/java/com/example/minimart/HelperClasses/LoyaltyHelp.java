package com.example.minimart.HelperClasses;

public class LoyaltyHelp {

    String userNIC,  Cardnumber;

    public LoyaltyHelp ( ) {
    }


    public LoyaltyHelp ( String userNIC , String Cardnumber ) {
        this.userNIC = userNIC;
        this. Cardnumber =  Cardnumber;
    }

    public String getUserNIC ( ) {
        return userNIC;
    }

    public void setUserNIC ( String userNIC ) {
        this.userNIC = userNIC;
    }

    public String getUserEmail ( ) {
        return  Cardnumber;
    }

    public void setUserEmail ( String userEmail ) {
        this. Cardnumber =  Cardnumber;
    }
}
