package com.devbaltasarq.burguerbuilder.core;

/**
 * Calculates costs related to a burguer.
 * Created by baltasarq on 5/10/17.
 */

public class BurguerConfigurator {
    public static final double BASE_COST = 3.0;

    public BurguerConfigurator()
    {
        this.totalCost = BASE_COST;

        this.selected = new boolean[] {
                false,
                false,
                false,
                false,
                false
        };

        assert ( INGREDIENTS.length != this.COSTS.length
                || INGREDIENTS.length != this.selected.length
                || COSTS.length != this.selected.length ):
                "all arrays should have the same length";
    }

    /** Calculates the cost for the burguer
     * @return A real with the total cost.
     */
    public double calculateCost()
    {
        double toret = BASE_COST;

        for(int i = 0; i < this.selected.length; ++i) {
            if ( this.selected[ i ] ) {
                toret += COSTS[ i ];
            }
        }

        return toret;
    }

    /** Goes back to the initial state, simple buguer, no ingredients at all */
    public void reset()
    {
        this.totalCost = BASE_COST;

        for(int i = 0; i < this.selected.length; ++i) {
            this.selected[ i ] = false;
        }

        return;
    }

    /** Returns the total cost with the current ingredient selection */
    public double getTotalCost()
    {
        return this.totalCost;
    }

    /** Returns the live boolean vector for ingredient selection */
    public boolean[] getSelected()
    {
        return this.selected;
    }

    /** Represents all available ingredients */
    public static String[] INGREDIENTS = new String[] {
            "Lechuga",
            "Tomate",
            "Queso",
            "York",
            "Cebolla"
    };

    /** Parallel array to ingredients, representing all costs */
    public static double[] COSTS = new double[] {
            0.1,
            0.5,
            1,
            0.75,
            0.1
    };

    private boolean[] selected;
    private double totalCost;
}
