package com.example.piecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CalculatedOrderActivity extends AppCompatActivity {
    ArrayList<PieOrder> pieOrders = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculated_order);

        //Get intent from MainActivity and deserialize the date.
        Intent i = getIntent();
        pieOrders = (ArrayList<PieOrder>) i.getSerializableExtra("pieOrders");

        //Inflate the list of the calculation result using ResultAdapter.
        ResultAdapter resultAdapter = new ResultAdapter(this, pieOrders);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(resultAdapter);
    }
}
