package com.company;

import java.io.*;
import java.util.ArrayList;

public class Connector {
    private String filename;

    Connector(String filename) {
        this.filename = filename;
    }

    public void writeProducts( ArrayList<Product> products) throws IOException {
        FileOutputStream fos = new FileOutputStream (filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeInt( products.size());
            for (Product product : products) {
                oos.writeObject(product);
            }
            oos.flush();
        }
    }
    public void writeOrders( ArrayList<Order> orders) throws IOException {
        FileOutputStream fos = new FileOutputStream (filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeInt( orders.size() );
            for (Order order : orders) {
                oos.writeObject(order);
            }
            oos.flush();
        }
    }
    void writeClients(ArrayList<Client> clients) throws IOException {
        FileOutputStream fos = new FileOutputStream (filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeInt( clients.size() );
            for (Client client: clients) {
                oos.writeObject(client);
            }
            oos.flush();
        }
    }
    public void writeDeals( ArrayList<Deal> deals) throws IOException {
        FileOutputStream fos = new FileOutputStream (filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeInt( deals.size() );
            for (Deal deal: deals) {
                oos.writeObject(deal);
            }
            oos.flush();
        }
    }
    void writeAdmins(ArrayList<Admin> admins) throws IOException {
        FileOutputStream fos = new FileOutputStream (filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeInt( admins.size() );
            oos.writeInt(Admin.blackList.size());
            for (Client cl: Admin.blackList)
            {
                oos.writeObject(cl);
            }
            for (Admin admin: admins) {
                oos.writeObject(admin);
            }
            oos.flush();
        }
    }

    ArrayList<Product> readProducts() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            int length = oin.readInt();
            ArrayList<Product> answer = new ArrayList<Product>();
            for ( int i = 0; i < length; i++ ) {
                answer.add((Product) oin.readObject());
            }
            return answer;
        }
    }
    public ArrayList<Order> readOrders() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            int length = oin.readInt();
            ArrayList<Order> answer = new ArrayList<Order>();
            for ( int i = 0; i < length; i++ ) {
                answer.add((Order) oin.readObject());
            }
            return answer;
        }
    }
    ArrayList<Client> readClients() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            int length = oin.readInt();
            ArrayList<Client> answer = new ArrayList<Client>();
            for ( int i = 0; i < length; i++ ) {
                answer.add((Client) oin.readObject());
            }
            return answer;
        }
    }
    public ArrayList<Deal> readDeals() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            int length = oin.readInt();
            ArrayList<Deal> answer = new ArrayList<Deal>();
            for ( int i = 0; i < length; i++ ) {
                answer.add((Deal) oin.readObject());
            }
            return answer;
        }
    }
    ArrayList<Admin> readAdmins() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            int length = oin.readInt();
            int blackLength = oin.readInt();
            for(int i = 0; i < blackLength; i++){
                Admin.blackList.add((Client) oin.readObject());
            }
            ArrayList<Admin> answer = new ArrayList<Admin>();
            for ( int i = 0; i < length; i++ ) {
                answer.add((Admin) oin.readObject());
            }
            return answer;
        }
    }

}