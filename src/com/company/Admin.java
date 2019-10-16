package com.company;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Admin implements Serializable {
    private final Date creationDate = new Date();
    private String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
        return dateFormatter.format(creationDate);
    }

    private String name;
    static ArrayList<Client> blackList = new ArrayList<>();

    public Admin(){
        name = "Mr. Smith";
    }
    public Admin(String name){
        this.name = name;
    }

    public Product MakeProduct(String product_name, int cost){
        Product answer;
        try {
            answer = new Product(product_name, cost, this);
        }
        catch (Exception ex)
        {
            System.err.println(ex.toString());
            return null;
        }
        return answer;
    }
    void AddToBlackList(Client t) throws Exception {
        if(blackList.contains(t)){
           throw new Exception(AppLocale.getString(AppLocale.error_already_in_blacklist));
        }

        blackList.add(t);
    }
    public Deal MakeDeal(Client cl, double cost) throws Exception {
        cl.TakeMoney(cost);
        return new Deal(cost);
    }
    void PayForOrder(Deal d, Order o) throws Exception {
        if(!o.AddDeal(d)){
            throw new Exception(AppLocale.getString(AppLocale.error_not_enough_money));
        }
    }

    public String GetName() {
        return name;
    }

    @Override
    public String toString() {
        return AppLocale.getString(AppLocale.name) + ": " + name + "; " + AppLocale.getString(AppLocale.creation_date)
                + ": " + getCreationDate() +")";
    }
}
