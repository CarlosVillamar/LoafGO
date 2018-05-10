package com.example.carlos.loafgotruckmodule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Carlos on 5/6/2018.
 */

public class OrdersDB {
    //DB constants
    public static final String DB_NAME = "Orders.db";
    public static final int DB_VERSION =1;

    //Order Table Constants
    public static final String ORDER_TABLE    = "Orders";

    public static final String ORDER_ID       = "_id";
    public static final int    ORDER_ID_COL   = 0;

    public static final String ORDER_NAME     ="order_name";
    public static final int    ORDER_NAME_COL =1;

    public static final String ORDER_ADDRESS     = "order_address";
    public static final int    ORDER_ADDRESS_COL = 2;

    public static final String ORDER_ORDER      = "orders";
    public static final int    ORDER_ORDERS_COL = 3;

    public static final String ORDER_QTY = "69";
    public static final int    ORDER_QTY_COL = 4;

    public static final String CREATE_LIST_TABLE =
            "CREATE TABLE" + ORDER_TABLE + "(" +
                             ORDER_ID      + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                             ORDER_NAME    + "TEXT," +
                             ORDER_ADDRESS + "TEXT,"+
                             ORDER_ORDER   + "TEXT,"+
                             ORDER_QTY     + "INTEGER)";

    public static final String DROP_LIST_TABLE =
        "DROP TABLE IF EXISTS" + ORDER_TABLE;

    static class DBHelper extends SQLiteOpenHelper{


        public DBHelper(Context context) {
            super(context,ORDER_TABLE, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //db.execSQL(CREATE_LIST_TABLE);
                db.execSQL(String.format("CREATE TABLE %s(%sINTEGER PRIMARY KEY AUTOINCREMENT,%sTEXT NOT NULL,%sTEXT NOT NULL,%sTEXT NOT NULL,%sINTEGER NOT NULL)", ORDER_TABLE, ORDER_ID, ORDER_NAME, ORDER_ADDRESS, ORDER_ORDER, ORDER_QTY));
            //insert orders
            db.execSQL("INSERT INTO Orders VALUES(1,'JOHN DOE','2265 Davidson Avenue,'"+"'banana bread'+'23')");
            db.execSQL("INSERT INTO Orders VALUES(2,'JANE DOE','2265 Davidson Avenue,'"+"'banana bread'+'46')");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(OrdersDB.DROP_LIST_TABLE);
            onCreate(db);
        }

    }

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public OrdersDB(Context context){
        dbHelper = new DBHelper(context);
    }

    private void openReadableDB(){
        db = dbHelper.getReadableDatabase();
    }
    private void openWriteableDB(){
        db = dbHelper.getWritableDatabase();
    }
    private void  closeDB(){
        if (db != null)
            db.close();
    }
    /*public ArrayList<Orders> getOrders(){
        this.openReadableDB();
        Cursor cursor = db.query(ORDER_TABLE,null,null,null,null,null,null,null);
        ArrayList<Orders> orders = new ArrayList<>();
        while (cursor.moveToNext()){
            orders.add(getOrderFromCursor(cursor));
        }
        if (cursor!=null)
            cursor.close();
        this.closeDB();
        return orders;
    }

    private Orders getOrderFromCursor(Cursor cursor) {
        if (cursor == null||cursor.getCount()==0){
            return null;
        }else {
            try {
                Orders orders = new Orders( cursor.getInt(ORDER_ID_COL),
                                            cursor.getString(ORDER_NAME_COL),
                                            cursor.getString(ORDER_ADDRESS_COL),
                                            cursor.getString(ORDER_ORDERS_COL),
                                            cursor.getString(ORDER_QTY_COL));
                                            return orders;
            }catch (Exception e){
                return null;
            }
        }

    }
    */

    public List<Orders> ordersList(String s){
        String q;
        if(s.equals("")){
            //regular query
            q = "SELECT * FROM "+ ORDER_TABLE;
        }else {
            //filter results by fliter option provided
            q = "SELECT * FROM " + ORDER_TABLE + "ORDER BY "+ s;
        }

        List<Orders> list = new LinkedList<>();
        //SQLiteDatabase db = this.openWriteableDB();
        Cursor cursor = db.rawQuery(q,null);
        Orders orders;

        if(cursor.moveToFirst()){
            do{
                orders = new Orders();

                orders.setId(cursor.getInt(cursor.getColumnIndex(ORDER_ID)));
                orders.setName(cursor.getString(cursor.getColumnIndex(ORDER_NAME)));
                orders.setAddress(cursor.getString(cursor.getColumnIndex(ORDER_ADDRESS)));
                orders.setOrder(cursor.getString(cursor.getColumnIndex(ORDER_ORDER)));
                orders.setQty(cursor.getInt(cursor.getColumnIndex(ORDER_QTY)));

                list.add(orders);
            }while (cursor.moveToNext());
        }
        return list;

    }




    public long insertOrder(Orders orders){
        ContentValues values = new ContentValues();
        values.put(ORDER_NAME,orders.getName());
        values.put(ORDER_ADDRESS,orders.getAddress());
        values.put(ORDER_ORDER,orders.getOrder());
        values.put(ORDER_QTY,orders.getQty());

        this.openWriteableDB();
        long rowID = db.insert(ORDER_TABLE,null, values);
        this.closeDB();

        return rowID;


        /**todo
         * write update ORDER function
         * write deleteOrder function
         *
         */
    }
}
