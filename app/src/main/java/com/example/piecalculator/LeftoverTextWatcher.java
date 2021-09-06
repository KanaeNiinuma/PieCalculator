package com.example.piecalculator;

import android.text.Editable;
import android.text.TextWatcher;

public class LeftoverTextWatcher implements TextWatcher {
    private PieOrder pieOrder;
    public LeftoverTextWatcher(PieOrder pieOrder) {
        this.pieOrder = pieOrder;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    // Save the input when the user type in the information.
    @Override
    public void afterTextChanged(Editable s) {
        if(s.length() > 0) {
            this.pieOrder.setLeftOvers(Integer.parseInt(s.toString()));
        }
    }
}
