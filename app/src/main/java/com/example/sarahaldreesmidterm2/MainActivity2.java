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

public class MainActivity2 extends AppCompatActivity {

    public static String idValue;
    public static String nameValue;
    public static String surnameValue;
    public static String nationalIdValue;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText id = (EditText) findViewById(R.id.editTextID);
        final EditText name = (EditText) findViewById(R.id.editTextName);
        final EditText email = (EditText) findViewById(R.id.editTextEmail);
        final EditText natID = (EditText) findViewById(R.id.editTextNatID);
        final Button add = (Button) findViewById(R.id.bttnAdd);
        final Button act1 = (Button) findViewById(R.id.button2);
        final Button act3 = (Button) findViewById(R.id.button3);
        final DatabaseHelper db = new DatabaseHelper(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idValue = id.getText().toString();
                nameValue = name.getText().toString();
                surnameValue = email.getText().toString();
                nationalIdValue = natID.getText().toString();

                db.AddData(idValue, nameValue, surnameValue, nationalIdValue);
                Toast.makeText(MainActivity2.this, "Successful Add", Toast.LENGTH_LONG).show();
            }
        });

        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity2.this,MainActivity.class));}
        });

        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity2.this,MainActivity3.class));}
        });
    }
}