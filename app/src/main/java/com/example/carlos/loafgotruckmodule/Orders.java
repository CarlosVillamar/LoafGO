package com.example.carlos.loafgotruckmodule;

/**
 * Created by user on 5/1/18.
 */

public class Orders  {

    static final String NAME_KEY ="John Doe";
    static final String Add_KEY = "Magnolia";
    static final String ORDER_KEY = "Banana Bread";
    static final String qty_key = "69";


    String name,address,order;
    int ID, qty;

    //Orders(int anInt, String string, String cursorString, String order, String s){}
    Orders(){}

    public Orders(String name, String address, String order, int qty) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.order = order;
        this.qty = qty;
    }

    public Orders(int ID,String name, String address, String order, int qty) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.order = order;
        this.qty = qty;

    }
        String getName(){return name;}

        String getAddress(){return address;}

        String getOrder(){return  order;}

        int getQty(){return qty;}

        //set string functions?


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    public void setQty(int qty){
        this.qty = qty;
    }
    public void setId(int ID){
        this.ID = ID;
    }
}
