package com.plan_it.mobile.plan_it;

/**
 * Created by Kristian on 13/12/2015.
 */
public class Budget {

    String userName;
    double totalPaid;
    boolean isPaying;
    String toPay;

    Budget(String userName, double totalPaid, boolean isPaying){
        this.userName = userName;
        this.totalPaid = totalPaid;
        this.isPaying = isPaying;
    }
}
