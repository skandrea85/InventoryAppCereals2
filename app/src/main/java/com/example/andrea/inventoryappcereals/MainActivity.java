package com.example.andrea.inventoryappcereals;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.andrea.inventoryappcereals.Data.CerealsContract.CerealsEntry;
import com.example.andrea.inventoryappcereals.Data.DatabaseCereals;

public class MainActivity extends AppCompatActivity {
    /**
     * Database helper that will provide us access to the database
     */
    private DatabaseCereals DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        DBHelper = new DatabaseCereals(this);

    }

    //Show CatalogActivity with solution code:
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase DB = DBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CerealsEntry._ID,
                CerealsEntry.Column_PRODUCT_NAME,
                CerealsEntry.Column_QUANTITY,
                CerealsEntry.Column_PRICE,
                CerealsEntry.Column_PARTNER_NAME,
                CerealsEntry.Column_PARTNER_CONTACT,
        };
        Cursor cursor = DB.query(
                CerealsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        TextView displayView = (TextView) findViewById(R.id.text_view_cereals);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The Warehouse contain " + cursor.getCount() + " cereals.\n\n");
            displayView.append(CerealsEntry._ID + " - " + " - " +
                    CerealsEntry.Column_PRODUCT_NAME + " - " +
                    CerealsEntry.Column_QUANTITY + " - " +
                    CerealsEntry.Column_PRICE + " - " +
                    CerealsEntry.Column_PARTNER_NAME + " - " +
                    CerealsEntry.Column_PARTNER_CONTACT + "\n");
            // Figure out the index of each column
            int idIndex = cursor.getColumnIndex(CerealsEntry._ID);
            int PNameIndex = cursor.getColumnIndex(CerealsEntry.Column_PRODUCT_NAME);
            int QuantityIndex = cursor.getColumnIndex(CerealsEntry.Column_QUANTITY);
            int PriceIndex = cursor.getColumnIndex(CerealsEntry.Column_PRICE);
            int PartnerNIndex = cursor.getColumnIndex(CerealsEntry.Column_PARTNER_NAME);
            int ContactIndex = cursor.getColumnIndex(CerealsEntry.Column_PARTNER_CONTACT);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {

                int extractID = cursor.getInt(idIndex);
                String extractPName = cursor.getString(PNameIndex);
                int extractQuantityIndex = cursor.getInt(QuantityIndex);
                int extractPriceNIndex = cursor.getInt(PriceIndex);
                String extractPartnerNIndex = cursor.getString(PartnerNIndex);
                String extractPartnerContact = cursor.getString(ContactIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + extractID + " - " +
                        extractPName + " - " +
                        extractQuantityIndex + " - " +
                        extractPriceNIndex + " - " +
                        extractPartnerNIndex + " - " +
                        extractPartnerContact
                ));

            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_product_data:
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
