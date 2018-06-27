package com.example.andrea.inventoryappcereals.Data;

import android.provider.BaseColumns;

public class CerealsContract {
    private CerealsContract() {
    }
    public static final class CerealsEntry implements BaseColumns {

        public final static String TABLE_NAME = "cereals";
        public final static String _ID = BaseColumns._ID;
        public final static String Column_PRODUCT_NAME = "product";
        public final static String Column_PRICE = "price";
        public final static String Column_QUANTITY = "quantity";
        public final static String Column_PARTNER_NAME = "partner";
        public final static String Column_PARTNER_CONTACT = "contact";

    }
}