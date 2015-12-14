package com.plan_it.mobile.plan_it;

/**
 * Created by Kristian on 13/12/2015.
 */
public class Budget {

    String userName;
    double totalActualCost;
    boolean isPaying;
    Budget(){}

    Budget(String userName, double totalActualCost, boolean isPaying){
        this.userName = userName;
        this.totalActualCost = totalActualCost;
        this.isPaying = isPaying;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(String userName){
        return userName;
    }
    public void setTotalActualCost(double cost){
        this.totalActualCost += cost;
    }
}
