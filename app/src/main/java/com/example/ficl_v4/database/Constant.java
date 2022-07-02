package com.example.ficl_v4.database;

public class Constant {
    private Constant() {}

    /** table names **/
    public static final String TABLE_USER = "user";
    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_MANUFACTURER = "manufacturer";
    public static final String TABLE_PRODUCT_IN_RATION = "product_in_ration";
//    public static final String TABLE_HISTORY = "history";


    public static final String _ID = "id";
    public static final String PROTEIN = "protein";
    public static final String FAT = "fat";
    public static final String CARBOHYDRATES = "carbohydrates";
    public static final String NAME = "name";
    public static final String WEIGHT = "weight";

    /** user **/
    // ID
    // NAME
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String HEIGHT = "height";
    // WEIGHT
    public static final String AGE = "age";
    public static final String STYLE_TRAINING = "style_training";
    // PFC
    public static final String ACTIVATED = "activated";
    public static final String METABOLISM = "metabolism";

    /** product **/
    // ID
    // NAME
    // PFC
    public static final String ID_MANUFACTURER = "id_manufacturer";

    /** Manufacturer **/
    // ID
    // NAME

    /** product in ration **/
    // ID
    // WEIGHT
    public static final String PERIOD_DAY = "period_day";
    public static final String ID_PRODUCT = "id_product";

//    /** history **/
//    // ID
//    // PFC
//    public static final String DATA = "data";
//    public static final String ID_USER = "id_user";


    /** create tables **/
    public static final String CREATE_TABLE_USER =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOGIN + " TEXT NOT NULL, " +
                    PASSWORD + " TEXT NOT NULL, " +
                    HEIGHT + " INTEGER NOT NULL, " +
                    WEIGHT + " INTEGER NOT NULL, " +
                    AGE + " INTEGER NOT NULL, " +
                    STYLE_TRAINING + " TEXT NOT NULL, " +
                    METABOLISM + " INTEGER NOT NULL, " +
                    ACTIVATED + " TEXT NOT NULL DEFAULT 'inactive')";
    public static final String CREATE_TABLE_PRODUCT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, " +
                    PROTEIN + " INTEGER NOT NULL, " +
                    FAT + " INTEGER NOT NULL, " +
                    CARBOHYDRATES + " INTEGER NOT NULL, " +
                    ID_MANUFACTURER + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + ID_MANUFACTURER + ") REFERENCES " + TABLE_MANUFACTURER + "(" + _ID + ") ON DELETE CASCADE)";
    public static final String CREATE_TABLE_MANUFACTURER =
            "CREATE TABLE IF NOT EXISTS " + TABLE_MANUFACTURER + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, " +
                    "UNIQUE (" + NAME + "))";
    public static final String CREATE_TABLE_PRODUCT_IN_RATION =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT_IN_RATION + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WEIGHT + " INTEGER NOT NULL, " +
                    PERIOD_DAY + " TEXT NOT NULL, " +
                    ID_PRODUCT + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + ID_PRODUCT + ") REFERENCES " + TABLE_PRODUCT + "(" + _ID + ") ON DELETE CASCADE)";
//    public static final String CREATE_TABLE_HISTORY =
//            "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY + " (" +
//                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    PROTEIN + " INTEGER NOT NULL, " +
//                    FAT + " INTEGER NOT NULL, " +
//                    CARBOHYDRATES + " INTEGER NOT NULL, " +
//                    DATA + " TEXT NOT NULL, " +
//                    ID_USER + " INTEGER NOT NULL, " +
//                    "FOREIGN KEY (" + ID_USER + ") REFERENCES " + TABLE_USER + "(" + _ID + ") ON DELETE CASCADE)";

    /** drop tables **/
    public static final String DELETE_TABLE_USER =
            "DROP TABLE IF EXISTS " + TABLE_USER;
    public static final String DELETE_TABLE_PRODUCT =
            "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    public static final String DELETE_TABLE_MANUFACTURER =
            "DROP TABLE IF EXISTS " + TABLE_MANUFACTURER;
    public static final String DELETE_TABLE_PRODUCT_IN_RATION =
            "DROP TABLE IF EXISTS " + TABLE_PRODUCT_IN_RATION;
//    public static final String DELETE_TABLE_HISTORY =
//            "DROP TABLE IF EXISTS " + TABLE_HISTORY;
}