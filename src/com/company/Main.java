package com.company;

import java.util.*;
import java.io.*;

public class Main {
    private static Scanner scn;

    static private Connector for_products;
    static private Connector for_orders;
    static private Connector for_clients;
    static private Connector for_deals;
    static private Connector for_admins;

    static private ArrayList<Product> allProducts;
    static private ArrayList<Order> allOrders;
    static private ArrayList<Client> allClients;
    static private ArrayList<Deal> allDeals;
    static private ArrayList<Admin> allAdmins;

    static private void Init() {
        for_products = new Connector("Products.dot");
        for_orders = new Connector("Orders.dot");
        for_clients = new Connector("Clients.dot");
        for_deals = new Connector("Deals.dot");
        for_admins = new Connector("Admins.dot");

        scn = new Scanner(System.in);
    }

    private static Locale createLocale(String[] args)	{
        if ( args.length == 2 ) {
            return new Locale( args[0], args[1] );
        } else if( args.length == 4 ) {
            return new Locale( args[2], args[3] );
        }
        return null;
    }
    private static void setupConsole(String[] args) {
        if ( args.length >= 2 ) {
            if ( args[0].compareTo("-encoding")== 0 ) {
                try {
                    System.setOut( new PrintStream( System.out,  true, args[1] ));
                } catch ( UnsupportedEncodingException ex ) {
                    System.err.println( "Unsupported encoding: " + args[1] );
                    System.exit(1);
                }
            }
        }
    }

    static private void ShowFunc() throws Exception {
        System.out.println("\t"+AppLocale.getString(AppLocale.what_you_want_to_see)+
                " : \n\t\t1."+AppLocale.getString(AppLocale.products)+
                "\n\t\t2."+AppLocale.getString(AppLocale.orders) +
                "\n\t\t3."+AppLocale.getString(AppLocale.clients) +
                "\n\t\t4."+AppLocale.getString(AppLocale.deals) +
                "\n\t\t5." + AppLocale.getString(AppLocale.admins));
        int answer = scn.nextInt();
        int i = 1;
        switch (answer){
            case 1:
                for (Product pr: allProducts) {
                    System.out.println("\t\t" + i + "." + pr.toString());
                    i++;
                }
                break;
            case 2:
                for (Order pr: allOrders) {
                    System.out.println("\t\t" + i + "." + pr.toString());
                    i++;
                }
                break;
            case 3:
                for (Client pr: allClients) {
                    System.out.println("\t\t" + i + "." + pr.toString());
                    i++;
                }
                break;
            case 4:
                for (Deal pr: allDeals) {
                    System.out.println("\t\t" + i + "." + pr.toString());
                    i++;
                }
                break;
            case 5:
                for (Admin pr: allAdmins) {
                    System.out.println("\t\t" + i + "." + pr.toString());
                    i++;
                }
                break;
            default:
                throw new Exception(AppLocale.getString(AppLocale.unknown_command)+ " " + answer);
        }
    }

    static private void AddFunc() throws Exception{
        System.out.println("\t"+AppLocale.getString(AppLocale.what_you_want_to_add)+"? : " +
                "\n\t\t1."+AppLocale.getString(AppLocale.products) +
                "\n\t\t2."+AppLocale.getString(AppLocale.clients) +
                "\n\t\t3."+AppLocale.getString(AppLocale.admins));
        int answer = scn.nextInt();
        switch (answer){
            case 1:
                System.out.println("\t\t" + AppLocale.getString(AppLocale.enter) + " "
                        +AppLocale.getString(AppLocale.name));
                scn.nextLine();
                String name = scn.nextLine();
                System.out.println("\t\t" + AppLocale.getString(AppLocale.enter) + " "
                        +AppLocale.getString(AppLocale.cost));
                int cost = scn.nextInt();
                allProducts.add(new Product(name, cost, allAdmins.get(0)));
                break;
            case 2:
                System.out.println("\t\t" + AppLocale.getString(AppLocale.enter) + " "
                        +AppLocale.getString(AppLocale.name));
                scn.nextLine();
                String name_of_client = scn.nextLine();
                allClients.add(new Client(name_of_client));
                break;
            case 3:
                System.out.println("\t\t" + AppLocale.getString(AppLocale.enter) + " "
                        +AppLocale.getString(AppLocale.name));
                scn.nextLine();
                String name_of_product = scn.nextLine();
                allAdmins.add(new Admin(name_of_product));
                break;
            default:
                throw new Exception(AppLocale.getString(AppLocale.unknown_command) +" " + answer);
        }
    }

    static private void WorkFunc() throws Exception {
        System.out.println("\t"+AppLocale.getString(AppLocale.what_do_you_want_work_with)+" : " +
                "\n\t\t1." + AppLocale.getString(AppLocale.clients)+
                "\n\t\t2." + AppLocale.getString(AppLocale.admins));
        int answer = scn.nextInt();
        switch (answer){
            case 1:
                WorkWithClient();
                break;
            case 2:
                WorkWithAdmin();
                break;
            default:
                throw new Exception(AppLocale.getString(AppLocale.unknown_command)+" " + answer);
        }
    }
    static private void WorkWithClient() throws Exception {
        System.out.println("\t\t"+AppLocale.getString(AppLocale.choose_the_client)+": ");
        int i = 1;
        for (Client pr: allClients) {
            System.out.println("\t\t\t" + i + "." + pr.toString());
            i++;
        }
        int clientIndex = scn.nextInt();
        Client cl = allClients.get(clientIndex-1);

        System.out.println("\t\t\t1."+AppLocale.getString(AppLocale.add_money) +
                "\n\t\t\t2."+AppLocale.getString(AppLocale.make_order) +
                "\n\t\t\t3." +AppLocale.getString(AppLocale.pay_order));
        int answer = scn.nextInt();
        switch (answer){
            case 1:
                System.out.println("\t\t\t\t"+AppLocale.getString(AppLocale.enter) +
                        AppLocale.getString(AppLocale.cost)+":");
                int a = scn.nextInt();
                cl.AddMoney(a);
                break;
            case 2:
                System.out.println("\t\t\t\t"+AppLocale.getString(AppLocale.enter_number_products)+": ");

                ArrayList<Product> newProducts = new ArrayList<>();

                int num = scn.nextInt();
                int k = 1;
                for (Product pr: allProducts) {
                    System.out.println("\t\t\t\t" + k + "." + pr.toString());
                    k++;
                }
                for(;num > 0; num--)
                {
                    int f = scn.nextInt();
                    if(f > allProducts.size() || f <= 0)
                        throw new Exception("Unknown product " + f);

                    newProducts.add(allProducts.get(f-1));
                }
                Order or = cl.MakeOrder(newProducts);
                allOrders.add(or);
                break;
            case 3:
                System.out.println("\t\t\t\t"+AppLocale.getString(AppLocale.choose_the_order)+": ");
                int w = 1;
                for (Order pr: allOrders) {
                    if(!pr.GetIsDone())
                        System.out.println("\t\t\t\t" + w + "." + pr.toString());
                    w++;
                }
                int payIndex = scn.nextInt();
                Deal d = new Deal(allOrders.get(payIndex-1).GetCost());
                cl.TakeMoney(d.GetCost());
                allAdmins.get(0).PayForOrder(d, allOrders.get(payIndex-1));
                allDeals.add(d);
                break;
            default:
                throw new Exception(AppLocale.getString(AppLocale.unknown_command)+" " + answer);
        }
    }
    static private void WorkWithAdmin() throws Exception {
        System.out.println("\t\t"+AppLocale.getString(AppLocale.choose_the_admin)+": ");
        int i = 1;
        for (Admin ad: allAdmins) {
            System.out.println("\t\t\t" + i + "." + ad.toString());
            i++;
        }
        int adminIndex = scn.nextInt();
        Admin ad = allAdmins.get(adminIndex-1);

        System.out.println("\t\t\t\t1."+AppLocale.getString(AppLocale.show_blacklist) +
                "\n\t\t\t\t2." + AppLocale.getString(AppLocale.add_to_blacklist));
        int answer = scn.nextInt();
        switch (answer) {
            case 1:
                int r = 1;
                for(Client c: Admin.blackList)
                {
                    System.out.println("\t\t\t\t\t"+r + "." + c.toString());
                }
                break;
            case 2:
                System.out.println("\t\t\t\t"+AppLocale.getString(AppLocale.choose_the_client)+": ");
                int t = 1;
                for (Client pr: allClients) {
                    System.out.println("\t\t\t\t\t" + t + "." + pr.toString());
                    t++;
                }
                int clientIndex = scn.nextInt();
                Client cl = allClients.get(clientIndex-1);
                ad.AddToBlackList(cl);
                break;
            default:
                throw new Exception(AppLocale.getString(AppLocale.unknown_command)+" " + answer);
        }
    }

    public static void main(String[] args) {
        setupConsole( args );
        Locale loc = createLocale( args );
        if ( loc == null ) {
            System.err.println(
                    "Invalid argument(s)\n" +
                            "Syntax: [-encoding ENCODING_ID] language country\n" +
                            "Example: -encoding Cp855 be BY" );
            System.exit(1);
        }
        AppLocale.set( loc );

        Init();

        try {
            System.out.print("\tReading Products file --- ");
            allProducts = new ArrayList<>();
            allProducts = for_products.readProducts();
            System.out.println("File is loaded");
        }
        catch (Exception i){
            System.err.println("Error in loaded Products file: " + i.toString());
            allProducts = new ArrayList<>();
        }
        System.out.println("Products: " + allProducts);


        try {
            System.out.print("\tReading Orders file --- ");
            allOrders = for_orders.readOrders();
            System.out.println("File is loaded");
        }
        catch (Exception i){
            System.err.println("Error in loaded Orders file: " + i.toString());
            allOrders = new ArrayList<>();
        }
        System.out.println("Orders: " + allOrders);


        try {
            System.out.print("\tReading Clients file --- ");
            allClients = for_clients.readClients();
            System.out.println("File is loaded");
        }
        catch (Exception i){
            System.err.println("Error in loaded Clients file: " + i.toString());
            allClients = new ArrayList<>();
        }
        System.out.println("Clients: " + allClients);


        try {
            System.out.print("\tReading Deals file --- ");
            allDeals = for_deals.readDeals();
            System.out.println("File is loaded");
        }
        catch (Exception i){
            System.err.println("Error in loaded Deals file: " + i.toString());
            allDeals = new ArrayList<>();
        }
        System.out.println("Deals: " + allDeals);


        try {
            System.out.print("\tReading Admins file --- ");
            allAdmins = for_admins.readAdmins();
            System.out.println("File is loaded");
        }
        catch (Exception i){
            System.err.println("Error in loaded Admins file: " + i.toString());
            allAdmins = new ArrayList<>();
        }
        System.out.println("Admins: " + allAdmins);

        System.out.println("\n\n\n\t\t\t");

        while (true) {
            try {

                System.out.println(AppLocale.getString(AppLocale.choose_what_you_want)+": " +
                        "\n\t1."+AppLocale.getString(AppLocale.see)+"." +
                        "\n\t2."+AppLocale.getString(AppLocale.add)+"." +
                        "\n\t3."+AppLocale.getString(AppLocale.work)+"." +
                        "\n\t4." + AppLocale.getString(AppLocale.finish));
                int answer = scn.nextInt();
                if(answer == 4)
                    break;

                switch (answer) {
                    case 1:
                        ShowFunc();
                        break;
                    case 2:
                        AddFunc();
                        break;
                    case 3:
                        WorkFunc();
                        break;
                    default:
                        throw new Exception(AppLocale.getString(AppLocale.unknown_command));
                }
            } catch (Exception ex) {
                System.err.println("Error with using program: " + ex.getMessage());
            }
        }

        try {
            for_products.writeProducts(allProducts);
            for_orders.writeOrders(allOrders);
            for_clients.writeClients(allClients);
            for_deals.writeDeals(allDeals);
            for_admins.writeAdmins(allAdmins);
        }
        catch (Exception ex) {
            System.err.println("Error in writing file");
        }
    }

}
