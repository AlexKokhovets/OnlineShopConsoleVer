package com.company;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Deal implements Serializable {
    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT);
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    private double cost;

    public Deal() {};
    Deal(double x){
        cost = x;
    }

    double GetCost(){
        return cost;
    }

    @Override
    public String toString() {
        return AppLocale.getString(AppLocale.cost)+": " + cost + "; "+
                AppLocale.getString(AppLocale.creation_date) + ": " + getCreationDate();
    }
}
