package com.example.a4_1phonephotoprintsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    int printsNum;
    double total, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //icon stuff p.140
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final EditText prints = (EditText) findViewById(R.id.printsNumInput);
        final RadioButton fourBySix = (RadioButton) findViewById(R.id.firstRB);
        final RadioButton fiveBySeven = (RadioButton) findViewById(R.id.secondRB);
        final RadioButton eightByTen = (RadioButton) findViewById(R.id.thirdRB);
        final TextView display = (TextView) findViewById(R.id.txtOutputDisplay);
        Button ordered = (Button) findViewById(R.id.btnOrder);
        ordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(""); //reset display
                try {
                    printsNum = Integer.parseInt(prints.getText().toString());
                    if (printsNum < 51) { //max 50 prints
                        if (fourBySix.isChecked() || fiveBySeven.isChecked() || eightByTen.isChecked()) {
                            if (fourBySix.isChecked()) {
                                price = .19;
                            } else if (fiveBySeven.isChecked()) {
                                price = .49;
                            } else if (eightByTen.isChecked()) {
                                price = .79;
                            }
                            total = price * printsNum;
                            DecimalFormat money = new DecimalFormat("$###.00");
                            display.setText("Your total is " + money.format(total));
                        } else {
                            Toast.makeText(MainActivity.this, "Select a size", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Maximum of 50 prints", Toast.LENGTH_SHORT).show();
                    }
                } catch(IllegalArgumentException ex) {
                    Toast.makeText(MainActivity.this, "Must enter a number 50 or less", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
