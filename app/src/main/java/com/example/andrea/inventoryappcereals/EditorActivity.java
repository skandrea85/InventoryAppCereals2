package com.example.andrea.inventoryappcereals;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrea.inventoryappcereals.Data.CerealsContract.CerealsEntry;
import com.example.andrea.inventoryappcereals.Data.DatabaseCereals;

public class EditorActivity extends AppCompatActivity {
    /**
     * EditText field to enter the pet's name
     */
    private EditText NameProductEditText;

    private EditText QuantityEditText;

    private EditText PriceEditText;

    private EditText PartnerNameEditText;

    private EditText PartnerContactEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        NameProductEditText = (EditText) findViewById(R.id.edit_name_product);


        QuantityEditText = (EditText) findViewById(R.id.quantity);


        PriceEditText = (EditText) findViewById(R.id.price);

        PartnerNameEditText = (EditText) findViewById(R.id.edit_name_partner);

        PartnerContactEditText = (EditText) findViewById(R.id.edit_Partner_contact);

    }

    private void InsertCereals() {

        String nameString = NameProductEditText.getText().toString().trim();

        String quantityString = QuantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);

        String priceString = PriceEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);

        String partner = PartnerNameEditText.getText().toString().trim();

        String contact = PartnerContactEditText.getText().toString().trim();


        DatabaseCereals DBHelper = new DatabaseCereals(this);
        SQLiteDatabase db = DBHelper.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(CerealsEntry.Column_PRODUCT_NAME, nameString);
        values.put(CerealsEntry.Column_QUANTITY, quantity);
        values.put(CerealsEntry.Column_PRICE, price);
        values.put(CerealsEntry.Column_PARTNER_NAME, partner);
        values.put(CerealsEntry.Column_PARTNER_CONTACT, contact);

        long newRowId = db.insert(CerealsEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving Product", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Product saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                InsertCereals();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

