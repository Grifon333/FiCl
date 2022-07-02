package com.example.ficl_v4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.ficl_v4.Product;

public class Manager {
    private Context context;
    private Helper dbHelper;
    private Cursor cursor;

    public Manager(Context context) {
        this.context = context;
        dbHelper = new Helper(context);
    }

    public void addProduct (Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.NAME, product.getName());
        values.put(Constant.PROTEIN, product.getProtein());
        values.put(Constant.FAT, product.getFat());
        values.put(Constant.CARBOHYDRATES, product.getCarbohydrate());
        values.put(Constant.ID_MANUFACTURER, product.getId_manufacturer());

        long result = db.insert(Constant.TABLE_PRODUCT, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public void addManufacturer(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.NAME, name);

        db.insert(Constant.TABLE_MANUFACTURER, null, values);
    }

    public int getIdManufacturer(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " + Constant._ID + " FROM " + Constant.TABLE_MANUFACTURER + " WHERE " + Constant.NAME + " = \'" + name + "\'";

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        int result = -1;
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                result = cursor.getInt(0);
            }
        }
        return result;
    }

    public int getIdProduct(Product product) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " + Constant._ID + " FROM " + Constant.TABLE_PRODUCT + " WHERE " +
                Constant.NAME + " = '" + product.getName() + "' AND " +
                Constant.PROTEIN + " = " + product.getProtein() + " AND " +
                Constant.FAT + " = " + product.getFat() + " AND " +
                Constant.CARBOHYDRATES + " = " + product.getCarbohydrate() + " AND " +
                Constant.ID_MANUFACTURER + " = " + product.getId_manufacturer();

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        int result = -1;
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                result = cursor.getInt(0);
            }
        }
        return result;
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " +
                Constant.TABLE_PRODUCT + "." + Constant.NAME + ", " +
                Constant.TABLE_PRODUCT + "." + Constant.PROTEIN + ", "  +
                Constant.TABLE_PRODUCT + "." + Constant.FAT + ", " +
                Constant.TABLE_PRODUCT + "." + Constant.CARBOHYDRATES + ", " +
                Constant.TABLE_MANUFACTURER + "." + Constant.NAME +
                " FROM " + Constant.TABLE_PRODUCT +
                " INNER JOIN " + Constant.TABLE_MANUFACTURER + " ON " +
                Constant.TABLE_MANUFACTURER + "." + Constant._ID + " = " +
                Constant.TABLE_PRODUCT + "." + Constant.ID_MANUFACTURER;

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public void updateProduct(String row_id, Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.NAME, product.getName());
        values.put(Constant.PROTEIN, product.getProtein());
        values.put(Constant.FAT, product.getFat());
        values.put(Constant.CARBOHYDRATES, product.getCarbohydrate());
        values.put(Constant.ID_MANUFACTURER, product.getId_manufacturer());

        long result = db.update(Constant.TABLE_PRODUCT, values, Constant._ID + "=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct(String row_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.delete(Constant.TABLE_PRODUCT, Constant._ID + "=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllProduct() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + Constant.TABLE_PRODUCT);
    }

    public Cursor searchProducts(String text) {
        text = text.toLowerCase();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT " +
                Constant.TABLE_PRODUCT + "." + Constant.NAME + ", " +
                Constant.TABLE_PRODUCT + "." + Constant.PROTEIN + ", "  +
                Constant.TABLE_PRODUCT + "." + Constant.FAT + ", " +
                Constant.TABLE_PRODUCT + "." + Constant.CARBOHYDRATES + ", " +
                Constant.TABLE_MANUFACTURER + "." + Constant.NAME +
                " FROM " + Constant.TABLE_PRODUCT +
                " INNER JOIN " + Constant.TABLE_MANUFACTURER + " ON " +
                Constant.TABLE_MANUFACTURER + "." + Constant._ID + " = " +
                Constant.TABLE_PRODUCT + "." + Constant.ID_MANUFACTURER +
                " WHERE " + Constant.TABLE_PRODUCT + "." + Constant.NAME + " GLOB " + "'*" + text + "*'";

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void addProductInRation(int weight, String periodDay, int id_product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.WEIGHT, weight);
        values.put(Constant.PERIOD_DAY, periodDay);
        values.put(Constant.ID_PRODUCT, id_product);

        long result = db.insert(Constant.TABLE_PRODUCT_IN_RATION, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getProductsInRation(String period_day) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " +
                Constant.TABLE_PRODUCT + "." + Constant.NAME + ", " +
                Constant.TABLE_PRODUCT + "." + Constant.PROTEIN + ", "  +
                Constant.TABLE_PRODUCT + "." + Constant.FAT + ", " +
                Constant.TABLE_PRODUCT + "." + Constant.CARBOHYDRATES + ", " +
                Constant.TABLE_PRODUCT + "." + Constant.ID_MANUFACTURER + ", " +
                Constant.TABLE_PRODUCT_IN_RATION + "." + Constant.WEIGHT +
                " FROM " + Constant.TABLE_PRODUCT_IN_RATION +
                " INNER JOIN " + Constant.TABLE_PRODUCT + " ON " +
                Constant.TABLE_PRODUCT + "." + Constant._ID + " = " +
                Constant.TABLE_PRODUCT_IN_RATION + "." + Constant.ID_PRODUCT +
                " WHERE " + Constant.TABLE_PRODUCT_IN_RATION + "." + Constant.PERIOD_DAY + " = '" + period_day + "'";

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public int getIdProductInDiary(int id_product, int weight, String period_day) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " + Constant._ID + " FROM " + Constant.TABLE_PRODUCT_IN_RATION + " WHERE " +
                Constant.WEIGHT + " = " + weight + " AND " +
                Constant.PERIOD_DAY + " = '" + period_day + "' AND " +
                Constant.ID_PRODUCT + " = " + id_product;

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        int result = -1;
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                result = cursor.getInt(0);
            }
        }
        return result;
    }

    public void updateProductInDiary(String row_id, int weight) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.WEIGHT, weight);

        long result = db.update(Constant.TABLE_PRODUCT_IN_RATION, values, Constant._ID + "=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProductInDiary(String row_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.delete(Constant.TABLE_PRODUCT_IN_RATION, Constant._ID + "=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void addUser(String login, String password, int weight, int height, int age, int select_active, String select_habit) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.LOGIN, login);
        values.put(Constant.PASSWORD, password);
        values.put(Constant.WEIGHT, weight);
        values.put(Constant.HEIGHT, height);
        values.put(Constant.AGE, age);
        values.put(Constant.STYLE_TRAINING, select_habit);

        double metabolism = getMetabolism(weight, height, age, select_active, select_habit);

        values.put(Constant.METABOLISM, (int) metabolism);
        values.put(Constant.ACTIVATED, "Active");


        long result = db.insert(Constant.TABLE_USER, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private double getMetabolism(int weight, int height, int age, int select_active, String select_habit) {
        double metabolism = 10 * weight + 6.25 * height - 5 * age + 5;
        switch (select_active) {
            case 1:
                metabolism *= 1.2;
                break;
            case 2:
                metabolism *= 1.38;
                break;
            case 3:
                metabolism *= 1.46;
                break;
            case 4:
                metabolism *= 1.55;
                break;
            case 5:
                metabolism *= 1.64;
                break;
            case 6:
                metabolism *= 1.73;
                break;
            case 7:
                metabolism *= 1.9;
                break;
        }

        if (select_habit.equals("Drying"))
            metabolism *= 0.85;
        else if (select_habit.equals("Mass"))
            metabolism *= 1.15;
        return metabolism;
    }

    public Cursor getUser() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " +
                Constant.LOGIN + ", " +
                Constant.HEIGHT + ", "  +
                Constant.WEIGHT + ", " +
                Constant.AGE + ", " +
                Constant.STYLE_TRAINING + ", " +
                Constant.METABOLISM +
                " FROM " + Constant.TABLE_USER +
                " WHERE " + Constant.ACTIVATED + " = 'Active'";

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public void deactivateAllUsers() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.ACTIVATED, "Inactive");

        db.update(Constant.TABLE_USER, values, null , null);
    }

    public Cursor getUserByLogin(String login) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " +
                Constant.PASSWORD +
                " FROM " + Constant.TABLE_USER +
                " WHERE " + Constant.LOGIN + " = '" + login + "'";

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public void ActivatedUser(String login) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.ACTIVATED, "Active");

        db.update(Constant.TABLE_USER, values, Constant.LOGIN + "=?" , new String[]{login});
    }

    public int getMetabolism() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " +
                Constant.METABOLISM +
                " FROM " + Constant.TABLE_USER +
                " WHERE " + Constant.ACTIVATED + " = 'Active'";

        cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        int result = 0;
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                result = cursor.getInt(0);
            }
        }

        return result;
    }
}