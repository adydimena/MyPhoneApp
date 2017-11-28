package com.example.ady.myphoneapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etname;
    EditText etnumber;
    EditText etemail;
    EditText etaddress;
    EditText etsport;

    private String TAG= MainActivity.class.getSimpleName();
    private Boolean isname;
    private Boolean isnumber;
    private Boolean isemail;
    private Boolean issport;
    private Boolean isaddress;
    private String sport;
    private String address;
    private String email;
    private String number;
    private String name;
    private Button btnGoto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Oncreate method");

    }


    public void AddContact(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        Log.d(TAG, "AddContact: onCliqued called");
        BindingView();
        if (CheackInputEmpty()) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

            Boolean isinserted = dataBaseHelper.insertData(name,number,email,address,sport);
            if (isinserted)
                Toast.makeText(this,"INSERTED",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"NOT INSERTED",Toast.LENGTH_LONG).show();
            btnGoto2 = findViewById(R.id.TogoActivity2);
            //btnGoto2.setVisibility(View.VISIBLE);

        }

        EmptyEditText();

    }

    private void EmptyEditText() {
        etname.setText("");
        etnumber.setText("");
        etemail.setText("");
        etaddress.setText("");
        etsport.setText("");

        SetHint();
    }

    private void SetHint() {
        etname.setHint("Enter New Name");
        etnumber.setHint("Enter New Number");
        etemail.setHint("Enter New Email");
        etaddress.setHint("Enter New Address");
        etsport.setHint("Enter New Sport");
    }

    private Boolean CheackInputEmpty() {
        if (  isname || isemail || isnumber || isaddress || issport)
        {
            Log.d(TAG, "AddContact: some empty");
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Incomplete Form")
                    .setMessage("Please Fill out ALL fields")
                    .create();
            alertDialog.show();
            return false;
        }
        return true;
    }

    private void BindingView() {
        etname = findViewById(R.id.EnterName);
        etnumber = findViewById(R.id.EnterNumber);
        etemail = findViewById(R.id.EnterEmial);
        etaddress = findViewById(R.id.EnterAddress);
        etsport = findViewById(R.id.EnterSport);

        name = etname.getText().toString();
        number = etnumber.getText().toString();
        email = etemail.getText().toString();
        address = etaddress.getText().toString();
        sport = etsport.getText().toString();

        isname = etname.getText().toString().trim().isEmpty();
        isnumber = etnumber.getText().toString().trim().isEmpty();
        isemail = etemail.getText().toString().trim().isEmpty();
        issport = etsport.getText().toString().trim().isEmpty();
        isaddress = etaddress.getText().toString().trim().isEmpty();
    }

    public void goTo2toDisplay(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
