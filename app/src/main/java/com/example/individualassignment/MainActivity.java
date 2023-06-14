package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCalculate, btnClear, btnAboutMe;
    EditText noElectricUsed, rebate;
    TextView tvOutput;
    String stringElectricUsed;
    double electricUsed;
    double rebatePercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        btnAboutMe = findViewById(R.id.btnAboutMe);

        noElectricUsed = findViewById(R.id.etElectricUsed);
        rebate = findViewById(R.id.etRebate);
        tvOutput = findViewById(R.id.tvOutput);

        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnAboutMe.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {

        if (view == btnCalculate) {

            stringElectricUsed = noElectricUsed.getText().toString();

            try {
                electricUsed = Double.parseDouble(stringElectricUsed);
            } catch (NumberFormatException nfe) {
                Toast.makeText(this, "Please Enter a Electricity Unit", Toast.LENGTH_SHORT).show();
                return;
            }

            if (electricUsed < 0) {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
                return;
            }

            String stringRebate = rebate.getText().toString();
            try {
                rebatePercentage = Double.parseDouble(stringRebate);
            } catch (NumberFormatException nfe) {
                Toast.makeText(this, "Please Enter Rebate Percentage", Toast.LENGTH_SHORT).show();
                return;
            }

            double bill = calculateElectricBill(electricUsed);
            double finalCost = calculateFinalCost(bill, rebatePercentage);
            tvOutput.setText("Estimated Electric Bill is RM " + String.format("%.2f", finalCost));

        } else if (view == btnClear) {
            noElectricUsed.setText("");
            rebate.setText("");
            tvOutput.setText("");

        } else if (view == btnAboutMe) {
            startActivity(new Intent(MainActivity.this, AboutMeActivity.class));

        }
    }

    private double calculateElectricBill(double electricUsed) {
        double totalBill = 0.0;

        if (electricUsed <= 200) {
            totalBill = electricUsed * 0.218;
        } else if (electricUsed <= 300) {
            totalBill = (200 * 0.218) + ((electricUsed - 200) * 0.334);
        } else if (electricUsed <= 600) {
            totalBill = (200 * 0.218) + (100 * 0.334) + ((electricUsed - 300) * 0.516);
        } else {
            totalBill = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((electricUsed - 600) * 0.546);
        }

        return totalBill;
    }

    private double calculateFinalCost(double totalBill, double rebatePercentage) {
        double rebateAmount = totalBill * (rebatePercentage / 100.0);
        return totalBill - rebateAmount;
    }


}
