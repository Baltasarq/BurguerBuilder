package com.devbaltasarq.burguerbuilder.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devbaltasarq.burguerbuilder.R;
import com.devbaltasarq.burguerbuilder.core.BurguerConfigurator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the burguer configuration
        this.cfgBurguer = new BurguerConfigurator();

        // Report relevant info
        Log.i( "MainActivity.OnCreate", "Number of ingredients: "
                + BurguerConfigurator.INGREDIENTS.length );

        for(int i = 0; i < BurguerConfigurator.INGREDIENTS.length; ++i) {
            Log.i( "MainActivity.OnCreate", "Availabel ingredient: "
                    + BurguerConfigurator.INGREDIENTS[ i ] );
        }

        Button btIngredients = (Button) this.findViewById( R.id.btIngredients );
        btIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.showIngredientsDialog();
            }
        });

        this.updateTotals();
    }

    private void updateTotals()
    {
        final TextView lblTotal = (TextView) this.findViewById( R.id.lblTotal );

        // Report
        Log.i( "MainActivity.updTotals", "Starting updating." );
        for(int i = 0; i < BurguerConfigurator.INGREDIENTS.length; ++i) {
            Log.i( "MainActivity.updTotals",
                    BurguerConfigurator.INGREDIENTS[ i ]
                    + " (" + BurguerConfigurator.COSTS[ i ] + ")"
                    + ": " + ( this.cfgBurguer.getSelected()[ i ] ? "Yes" : "No" )
            );
        }

        Log.i( "MainActivity.updTotals", "Total cost: "  + this.cfgBurguer.calculateCost() );

            // Update
        lblTotal.setText( Double.toString( MainActivity.this.cfgBurguer.calculateCost() ) );
        Log.i( "MainActivity.updTotals", "End updating." );
    }

    private void showIngredientsDialog()
    {
        final boolean[] selections = this.cfgBurguer.getSelected();
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        dlg.setTitle( "Ingredients" );

        dlg.setMultiChoiceItems(
                BurguerConfigurator.INGREDIENTS,
                selections,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        selections[ i ] = b;
                    }
                }
        );

        dlg.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.updateTotals();
            }
        });
        dlg.create().show();
    }

    private BurguerConfigurator cfgBurguer;
}
