package com.example.piecalculator;

import android.text.Editable;
import android.text.TextWatcher;

public class SpecialTextWatcher implements TextWatcher {
    private PieOrder pieOrder;
    public SpecialTextWatcher(PieOrder pieOrder) {
        this.pieOrder = pieOrder;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        if(s.length() > 0) {
            this.pieOrder.setSpecialOrders(Integer.parseInt(s.toString()));
        }
    }
}
