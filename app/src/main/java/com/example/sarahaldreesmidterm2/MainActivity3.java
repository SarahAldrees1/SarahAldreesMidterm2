package com.example.sarahaldreesmidterm2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    public static String idValue;
    public static String nameValue;
    public static String surnameValue;
    public static String nationalIdValue;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final EditText id = (EditText) findViewById(R.id.editTextID);
        final EditText name = (EditText) findViewById(R.id.editTextName);
        final EditText email = (EditText) findViewById(R.id.editTextEmail);
        final EditText natId = (EditText) findViewById(R.id.editTextNatID);
        final Button viewData = (Button) findViewById(R.id.buttonView2);
        final Button searchData = (Button) findViewById(R.id.buttonSearch);
        TextView result = (TextView) findViewById(R.id.disp_res);
        final DatabaseHelper db = new DatabaseHelper(this);
        final Button act1 = (Button) findViewById(R.id.button);
        final Button act2 = (Button) findViewById(R.id.button2);
        final Button deleteData = (Button) findViewById(R.id.buttonDelete);

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.ViewData();
                StringBuffer buffer = new StringBuffer();

                while (cur.moveToNext()) {

                    buffer.append("ID: " + cur.getString(0) + "\n");
                    buffer.append("Name: " + cur.getString(1) + "\n");
                    buffer.append("Surname: " + cur.getString(2) + "\n");
                    buffer.append("National ID: " + cur.getString(3) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);
                builder.setTitle("All Data");
                builder.setMessage(buffer.toString());
                builder.show();

                Toast.makeText(MainActivity3.this, "Successful View", Toast.LENGTH_LONG).show();

            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idValue = id.getText().toString();
                db.DeleteData(idValue);

                Toast.makeText(MainActivity3.this, "Successful Delete", Toast.LENGTH_LONG).show();

            }
        });

        searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    idValue = id.getText().toString();
                    db.getSpecificResult(idValue);
                } catch (Exception e) {
                    count = 1;
                    Toast.makeText(MainActivity3.this, "Unsuccessful Search please insert id", Toast.LENGTH_LONG).show();
                }
            }
        });

        searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idValue = id.getText().toString();

                if (idValue.equals(""))
                    Toast.makeText(MainActivity3.this, "make sure id is filled", Toast.LENGTH_SHORT).show();

                else {
                    Cursor search = db.structuredQuery(idValue);

                    if (search.getCount() == 0) {
                        Toast.makeText(MainActivity3.this, "no data found", Toast.LENGTH_SHORT).show();
                    } else {
                        result.setText("" + search);
                        System.out.print(search);
                        Log.d("Sarah", "" + search.getString(0));
                        id.setText(search.getString(0));
                        name.setText(search.getString(1));
                        email.setText(search.getString(2));
                        natId.setText(search.getString(3));
//                        pr.setText(search.getString(3));
                        result.setText("Query: \nID: " + id.getText().toString() +
                                "\nName: " + name.getText().toString() +
                                "\nEmail: " + email.getText().toString() +
                                "\nNational ID: " + natId.getText().toString());
                    }
                }
            }
        });

        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity3.this,MainActivity.class));}
        });

        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity3.this,MainActivity2.class));}
        });
    }
}