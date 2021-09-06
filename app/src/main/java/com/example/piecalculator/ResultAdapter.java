package com.example.piecalculator;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultAdapter extends ArrayAdapter <PieOrder> {
    private static Map<String, Integer> iconMap;
    static {
        iconMap = new HashMap<>();
        iconMap.put("Apple", R.drawable.apple);
        iconMap.put("Blueberry", R.drawable.blueberrry);
        iconMap.put("Mixed berry", R.drawable.mixed_berry);
        iconMap.put("Raspberry", R.drawable.raspberry);
        iconMap.put("Cherry", R.drawable.cherry);
        iconMap.put("New England Bog", R.drawable.new_england_bog);
        iconMap.put("Pecan", R.drawable.pecan);
        iconMap.put("Sweet Potato", R.drawable.sweet_potato);
        iconMap.put("Pork", R.drawable.pork);
        iconMap.put("Peach", R.drawable.peach);
        iconMap.put("Peach Raspberry", R.drawable.peach_raspberry);
        iconMap.put("Peach Blueberry", R.drawable.peach_blueberry);
        iconMap.put("Strawberry Rhubarb", R.drawable.strawberry_rhubarb);
        iconMap.put("Black and Blue", R.drawable.black_and_blue);
        iconMap.put("Chicken", R.drawable.chicken);
    }
    public ResultAdapter(Context context, ArrayList<PieOrder> pieOrders){
        super(context, 0, pieOrders);
    }
    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.result_item_list, parent, false);
        }

        // Get the object located at this position in the list
        PieOrder currentPie = getItem(position);

        if(iconMap.get(currentPie.getPieFlavor()) != null) {
            ImageView icon = listItemView.findViewById(R.id.image);
            icon.setImageDrawable(getContext().getResources().getDrawable(iconMap.get(currentPie.getPieFlavor())));
        }


        TextView type = listItemView.findViewById(R.id.type);
        type.setText(currentPie.getPieFlavor());

        TextView size = listItemView.findViewById(R.id.size);
        size.setText(currentPie.getPieSize());

        TextView total = listItemView.findViewById(R.id.qty);
        total.setText(Integer.toString(currentPie.calculateTotal()));

        return listItemView;
    }

}


