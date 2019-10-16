package com.company;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT);
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    private ArrayList<Product> products;
    private double cost;
    private boolean is_done;
    private Client client;

    Order(){
        products = new ArrayList<Product>();
        cost = 0;
        is_done = false;
        client = null;
    }
    Order(ArrayList<Product> a, Client c) {
        products = a;
        CountTheCost();
        is_done = false;
        client = c;
    }

    public double GetCost() {
        return cost;
    }
    public boolean GetIsDone(){
        return is_done;
    }

    public void AddToOrder(Product pr) throws Exception {
        if(!is_done) {
            products.add(pr);
            cost += pr.GetCost();
        }
        else {
            throw new Exception(AppLocale.getString(AppLocale.error_order_is_done));
        }
    }
    public void RemoveFromOrder(Product pr) throws Exception {
        if(!is_done){
            if(products.remove(pr)) {
                cost -= pr.GetCost();
            }
            else{
                throw new Exception(AppLocale.getString(AppLocale.error_product_is_not_in_order));
            }
        }
        else {
            throw new Exception(AppLocale.getString(AppLocale.error_order_is_done));
        }
    }

    public boolean AddDeal(Deal d){
        if(is_done)
            return false;
        if(d.GetCost() >= cost){
            is_done = true;
        }
        return is_done;
    }

    private void CountTheCost() {
        cost  = 0;
        for (Product pr: products) {
            cost += pr.GetCost();
        }
    }

    @Override
    public String toString() {
        String answer =  AppLocale.getString(AppLocale.client)+": " + client.toString() + "; "+
                AppLocale.getString(AppLocale.cost)+": " + cost + "; " +
                AppLocale.getString(AppLocale.status) + ": "+is_done + "; ";
        for (Product pr: products) {
            answer += pr.toString() + " ";
        }
        answer += "; " +AppLocale.getString(AppLocale.creation_date) + ": "+ getCreationDate();
        return answer;
    }
}
