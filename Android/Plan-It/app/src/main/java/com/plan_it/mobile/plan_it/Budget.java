package com.plan_it.mobile.plan_it;

/**
 * Created by Kristian on 13/12/2015.
 */
public class Budget {
    //int userId;
    String userName;
    double dividedTotal;
    double sumActCost;
    double toPay;
    boolean isPaying;

    Budget(/*int userId,*/String userName,double sumActCost, double dividedTotal,double toPay, boolean isPaying){
        //this.userId = userId;
        this.userName = userName;
        this.sumActCost = sumActCost;
        this.dividedTotal = dividedTotal;
        this.toPay = toPay;
        this.isPaying = isPaying;
    }
}
