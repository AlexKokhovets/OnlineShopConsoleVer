package com.company;

import java.util.*;

public class AppLocale {
    private static final String strMsg = "Msg";
    private static Locale loc = new Locale("en", "GB");
    private static ResourceBundle res =
            ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.loc);

    static Locale get() {
        return AppLocale.loc;
    }

    static void set(Locale loc) {
        AppLocale.loc = loc;
        res = ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.loc);
    }

    static ResourceBundle getBundle() {
        return AppLocale.res;
    }

    static String getString(String key) {
        return AppLocale.res.getString(key);
    }

    // Resource keys:

    static final String name = "name";
    public static final String cost = "cost";
    static final String money = "money";
    public static final String creation_date = "creation_date";
    public static final String client = "client";

    static final String error_already_in_blacklist = "error_already_in_blacklist";
    static final String error_not_enough_money = "error_not_enough_money";
    static final String error_invalid_format_of_input = "error_invalid_format_of_input";
    static final String error_not_have_rights = "error_not_have_rights";
    static final String error_order_is_done = "error_order_is_done";
    static final String error_product_is_not_in_order = "error_product_is_not_in_order";
    static final String status = "status";
    static final String unknown_command = "unknown_command";

    static final String what_you_want_to_see = "what_you_want_to_see";
    static final String what_you_want_to_add = "what_you_want_to_add";
    static final String what_do_you_want_work_with = "what_do_you_want_work_with";
    static final String choose_what_you_want = "choose_what_you_want";

    static final String products = "products";
    static final String clients = "clients";
    static final String orders = "orders";
    static final String admins = "admins";
    static final String deals = "deals";

    static final String enter = "enter";
    static final String see = "see";
    static final String add = "add";
    static final String work = "work";
    static final String finish = "finish";

    static final String choose_the_client = "choose_the_client";
    static final String choose_the_order = "choose_the_order";
    static final String choose_the_admin = "choose_the_admin";

    static final String add_money = "add_money";
    static final String make_order = "make_order";
    static final String pay_order = "pay_order";

    static final String show_blacklist = "show_blacklist";
    static final String add_to_blacklist = "add_to_blacklist";

    static final String enter_number_products = "enter_number_products";
}