package com.company;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Client implements Serializable {
    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT);
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    private String name;
    private double money;

    public Client(){
        name = "Mr. Smith";
        money = 0;
    }
    public Client(String name){
        this.name = name;
        money = 0;
    }

    void TakeMoney(double a) throws Exception {
        if(a > money){
            throw new Exception(AppLocale.getString(AppLocale.error_not_enough_money));
        }
        money -= a;
    }

    public void AddMoney(double a) throws Exception {
        if(a < 0){
            throw new Exception(AppLocale.getString(AppLocale.error_invalid_format_of_input));
        }
        money += a;
    }

    public Order MakeOrder(ArrayList<Product> a){
        return new Order(a, this);
    }

    @Override
    public String toString() {
        return AppLocale.getString(AppLocale.name) + ": " + name + "; " + AppLocale.getString(AppLocale.money) + ": "
                + money + "; " + AppLocale.getString(AppLocale.creation_date) + ": " + getCreationDate();
    }
}
