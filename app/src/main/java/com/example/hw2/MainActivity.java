package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float apartmentPrice = 14_000;
    int account = 1_000;
    float wage = 2_500;
    int percentFree = 100;
    float percentBank = 5;
    float[] monthlyPayments = new float[120];

    private float apartmentPriseWithContribution() {
        return apartmentPrice - account;
    }

    public float mortgageCosts(float amount, int percent) {
        return (amount*percent)/100;
    }

    public int countMonth(float total, float mortgageCoast, float percentBankYear) {

        float percentBankMonth = percentBankYear / 12;
        int count = 0;

        while (total>0) {
            count++;
            total=(total +(total*percentBankMonth)/100)-mortgageCoast;
            if (total>mortgageCoast) {
                monthlyPayments[count-1]=mortgageCoast;
            }else {
                monthlyPayments[count-1]=total;
            }
        }
        return count;
    }

    private TextView countOut;
    private TextView manyMonthOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countOut = findViewById(R.id.countOut);
        manyMonthOut = findViewById(R.id.manyMonthOut);

        countOut.setText("Ипотека будет выплачиваться " + countMonth(apartmentPriseWithContribution(), mortgageCosts(wage, percentFree), percentBank) + " месяцев");
        String monthlyPaymentsList = " ";
        for (float list : monthlyPayments) {
            if (list>0) {
                monthlyPaymentsList = monthlyPaymentsList + Float.toString(list) + " монет ";
            }else {
                break;
            }
        }
        manyMonthOut.setText("Первоначальный взнос " + account + " монет, ежемесячные выплаты: " + monthlyPaymentsList);
    }
}