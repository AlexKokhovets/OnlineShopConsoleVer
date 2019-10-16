package com.company;

import java.io.Serializable;
import java.lang.Exception;
import java.text.DateFormat;
import java.util.Date;

public class Product implements Serializable {

    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT);
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    private String name;
    private double cost;

    public Product() throws Exception {
        name = "null";
        SetCost(0);
    }
    Product(String name, double cost, Admin admin) throws Exception {
        if(admin == null)
            throw new Exception(AppLocale.getString(AppLocale.error_not_have_rights));

        this.name = name;
        SetCost(cost);
    }

    String GetName()
    {
        return name;
    }
    double GetCost()
    {
        return cost;
    }
    void SetCost(double a) throws Exception {
        if(a < 0)
        {
            throw new Exception(AppLocale.getString(AppLocale.error_invalid_format_of_input));
        }
        cost = a;
    }

    @Override
    public String toString() {
        return AppLocale.getString(AppLocale.name) + ": " + name + "; " +
                AppLocale.getString(AppLocale.cost) + ": " + cost + "; " +
                AppLocale.getString(AppLocale.creation_date) + ": " + getCreationDate();
    }
}
