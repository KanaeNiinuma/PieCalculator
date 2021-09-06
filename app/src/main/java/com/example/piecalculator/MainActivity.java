package com.example.piecalculator;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    //get the current date for findSeason method.
    private Date date = new Date();
    private final String[] WEEKDAYS = {"Mon", "Tue", "Wed", "Thu"};
    private ArrayList<PieOrder> pieOrders = new ArrayList<>();
    private RecyclerView pieRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieRecyclerView = findViewById(R.id.file);
        //Improve performance if you know that changes in content do not change the layout size of the RecyclerView.
        pieRecyclerView.setHasFixedSize(true);
        //Use a linear layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        pieRecyclerView.setLayoutManager(layoutManager);


        TextView today = findViewById(R.id.date);
        //Format the date to display.
        SimpleDateFormat pattern = new SimpleDateFormat("E MM/dd/yyyy");
        today.setText(pattern.format(date));
        //Load the JSON file.
        loadFile();

        //Find the view that shows the result.
        Button calculate = findViewById(R.id.calculate);
        //Set a clickListener on the calculate button.
        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Create a new intent
                Intent resultIntent = new Intent(MainActivity.this, CalculatedOrderActivity.class);
                //Send the Pie object data to the CalculatedOrderActivity page.
                resultIntent.putExtra("pieOrders", pieOrders);
                startActivity(resultIntent);
            }
        });

        //find the view that shows clear
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFile();
            }
        });
    }



    /* Take in a Date object and find the season.
     * @param String date
     * @return String current season
     */
    public String findSeason(Date date){
        int month = date.getMonth();
        String season = null;
        if(month >= 3){
            season = "spring";
        }
        if(month >= 6){
            season = "summer";
        }
        if(month >= 9){
            season = "fall";
        }
        if(month >= 12){
            season = "winter";
        }
        return season;
    }

    /* Takes in a Date object to determine the day of week.
     * @param date object
     * @return String  the day of week
     */
    public String findDayOfWeek(Date date){
        //Specify the format by using SimpleDateFormat
        SimpleDateFormat pattern = new SimpleDateFormat("E");
        //Convert the date into a specified format
        String currentDayOfWeek = pattern.format(date);

        //Iterate through the array of weekdays to check the day.
        for(String day: WEEKDAYS){
            if(day.equals(currentDayOfWeek)){
                return "weekday";
            }
        }
        return "weekend";
    }

    /* Loads a JSON file from raw folder.
     */
    public void loadFile() {
        Resources res = getResources();
        InputStream inputStream = res.openRawResource(R.raw.pies);

        Scanner scan = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        while(scan.hasNextLine()){
            stringBuilder.append(scan.nextLine());
        }
        parseJson(stringBuilder.toString());
    }

    /* Parses the JSON data and organizes to display.
     * @param String text information of the pie data
     */
    private void parseJson(String data) {

        try {
            JSONObject root = new JSONObject(data);
            String currentSeason = findSeason(date);
            String dayOfWeek = findDayOfWeek(date);
            JSONObject season = root.getJSONObject(currentSeason);
            JSONArray orders = season.getJSONArray(dayOfWeek);

            pieOrders.clear();

            //Iterate through the JSON array to create and add PieOrder object and store in an ArrayList.
            for(int i = 0; i < orders.length(); i++) {
                JSONObject pie = orders.getJSONObject(i);
                pieOrders.add(new PieOrder(pie.getString("type")
                        , pie.getString("size"), pie.getInt("qty")));
            }

            OrderInputAdapter orderInputAdapter = new OrderInputAdapter(pieOrders);
            pieRecyclerView.setAdapter(orderInputAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}