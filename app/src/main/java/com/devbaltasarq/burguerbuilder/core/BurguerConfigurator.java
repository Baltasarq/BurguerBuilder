package com.devbaltasarq.burguerbuilder.core;

/**
 * Calculates costs related to a burguer.
 * Created by baltasarq on 5/10/17.
 */

public class BurguerConfigurator {
    public static final String DELIMITER = ", ";
    public static final double BASE_COST = 3.0;

    public BurguerConfigurator()
    {
        this.selected = new boolean[ INGREDIENTS.length ];

        assert INGREDIENTS.length != COSTS.length:
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

    /** Changes the selection of the ingredient in the given pos with value. */
    public void setSelected(int pos, boolean value)
    {
        this.selected[ pos ] = value;
    }

    /** Returns the live boolean vector for ingredient selection */
    public boolean[] getSelected()
    {
        return java.util.Arrays.copyOf( this.selected, this.selected.length );
    }

    @Override
    public String toString()
    {
        String delimiter = "";
        StringBuilder toret = new StringBuilder();

        for(int i = 0; i < this.selected.length; ++i) {
            if ( this.selected[ i ] ) {
                toret.append( delimiter );
                toret.append( INGREDIENTS[ i ] );
                delimiter = DELIMITER;
            }
        }

        return toret.toString();
    }

    /** Returns selected ingredients as a string.
     *  @returns A single string with the whole contents.*/
    public String toList()
    {
        return this.toListWith( DELIMITER );
    }

    /** Returns selected ingredients as a string.
     *  @param newDelimiter The delimiter to use to end each line.
     *  @returns A single string with the whole contents.*/
    public String toListWith(String newDelimiter)
    {
        return this.toString().replace( DELIMITER, newDelimiter );
    }

    /** Returns the number of ingredients available. */
    public static int getNumIngredients()
    {
        return INGREDIENTS.length;
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
}
