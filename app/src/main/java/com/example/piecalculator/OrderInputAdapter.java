package com.example.piecalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class OrderInputAdapter extends RecyclerView.Adapter<OrderInputAdapter.ViewHolder> {
    private final ArrayList<PieOrder> pieOrders;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView flavor;
        public TextView size;
        public TextView parNum;
        public EditText leftovers;
        public EditText specials;

        public ViewHolder(View view){
            super(view);

            flavor = view.findViewById(R.id.pie_flavor);
            size = view.findViewById(R.id.pie_size);
            parNum = view.findViewById(R.id.pie_par);
            leftovers = view.findViewById(R.id.leftover);
            specials = view.findViewById(R.id.special_order);
        }
    }
        public OrderInputAdapter(ArrayList<PieOrder> pies){
            pieOrders = pies;
    }

    @Override
    public OrderInputAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View listOfPies = inflater.inflate(R.layout.list_pie_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(listOfPies);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderInputAdapter.ViewHolder holder, int position) {
        // get element from the dataset at this position
        PieOrder current = pieOrders.get(position);
        TextView flavor = holder.flavor;
        flavor.setText(current.getPieFlavor());
        TextView size = holder.size;
        size.setText(current.getPieSize());
        TextView par = holder.parNum;
        par.setText(Integer.toString(current.getPieQty()));
        EditText leftover = holder.leftovers;
        leftover.addTextChangedListener(new LeftoverTextWatcher(current));
        EditText special = holder.specials;
        special.addTextChangedListener(new SpecialTextWatcher(current));
    }




    @Override
    public int getItemCount() {
        if(pieOrders != null){
        return pieOrders.size();
    } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
