package com.example.piecalculator;

import androidx.annotation.NonNull;
import java.io.Serializable;


public class PieOrder implements Serializable {
    private String pieFlavor;
    private String pieSize;
    private int parNumber;
    private int leftOvers;
    private int specialOrders;
    private int piesToBake;

    /* Constructor for a PieOrder object.
     * @param String flavor
     * @param String size
     * @param int qty
     */
    public PieOrder(String flavor, String size, int qty) {
        this.pieFlavor = flavor;
        this.pieSize = size;
        this.parNumber = qty;
    }

    /* Accessor for the pie flavor
     * return pieFlavor
     */
    public String getPieFlavor() {
        return pieFlavor;
    }

    /* Accessor for the pie size
     * return pieSize
     */
    public String getPieSize() {
        return pieSize;
    }

    /* Accessor for the pie par number
     * return parNumber
     */
    public int getPieQty() {
        return parNumber;
    }

    /* Mutator for the leftover
     *
     */
    public void setLeftOvers(int leftoverPie) {
        this.leftOvers = leftoverPie;
    }

    /* Mutator for the leftover
    *
    */
    public void setSpecialOrders(int specialPie) {
        this.specialOrders = specialPie;
    }

    /* Calculate the total number of pies to bake.
     * return int the number of pies to bake.
     */
    public int calculateTotal() {
        int goal = 0;
        int leftoverNum = this.leftOvers;
        int specialNum = this.specialOrders;
        int parNum = this.parNumber;

        if (parNum > leftoverNum) {
            goal = parNum - leftoverNum;
        }
        piesToBake = goal + specialNum;

        return piesToBake;
    }

    /* Append each field of PieOrder object as a string
     * return String shows the detail of the PieOrder object
     */
    @Override
    @NonNull
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nPie Order:\n");
        builder.append("Pie Flavor: ");
        builder.append(pieFlavor);
        builder.append("\n");
        builder.append("Pie Size: ");
        builder.append(pieSize);
        builder.append("\n");
        builder.append("Par Number: ");
        builder.append(parNumber);
        builder.append("\n");
        builder.append("LeftOver Number: ");
        builder.append(leftOvers);
        builder.append("\n");
        builder.append("Specials Number: ");
        builder.append(specialOrders);
        builder.append("\n");
        return builder.toString();
    }

}
