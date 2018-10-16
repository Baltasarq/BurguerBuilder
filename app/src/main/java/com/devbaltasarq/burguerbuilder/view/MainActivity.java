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

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_main );

        // Create the burguer configuration
        this.cfgBurguer = new BurguerConfigurator();

        // Report relevant info
        Log.i( "MainActivity.OnCreate", "Number of ingredients: "
                + BurguerConfigurator.INGREDIENTS.length );

        for(int i = 0; i < BurguerConfigurator.INGREDIENTS.length; ++i) {
            Log.i( "MainActivity.OnCreate", "Available ingredients: "
                    + BurguerConfigurator.INGREDIENTS[ i ] );
        }

        // Create callback for the button allowing to check prices
        final Button btPrices = (Button) this.findViewById( R.id.btPrices );
        btPrices.setOnClickListener( (v) -> this.showPricesDialog() );

        // Create callback for the button allowing to select ingredients
        final Button btIngredients = (Button) this.findViewById( R.id.btIngredients );
        btIngredients.setOnClickListener( (v) -> this.showIngredientsDialog() );
        this.updateTotals();
    }

    private void updateTotals()
    {
        final TextView lblTotal = (TextView) this.findViewById( R.id.lblTotal );
        final TextView lblSelection = (TextView) this.findViewById( R.id.lblIngredientsSelected );

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
        lblTotal.setText(
                String.format( Locale.getDefault(),
                               "%5.2f",
                                MainActivity.this.cfgBurguer.calculateCost() ) );
        lblSelection.setText( "─" + this.cfgBurguer.toListWith( "\n─" ) );
        Log.i( "MainActivity.updTotals", "End updating." );
    }

    private void showIngredientsDialog()
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );

        dlg.setTitle( this.getResources().getString( R.string.lblIngredientSelection) );

        dlg.setMultiChoiceItems(
                BurguerConfigurator.INGREDIENTS,
                this.cfgBurguer.getSelected(),
                (d, pos, value) -> {
                    this.cfgBurguer.setSelected( pos, value );
                });

        dlg.setPositiveButton( "Ok", (v, i) -> this.updateTotals() );
        dlg.create().show();
    }

    private void showPricesDialog()
    {
        final StringBuilder desc = new StringBuilder();
        final TextView lblData = new TextView( this );
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        dlg.setTitle( this.getResources().getString( R.string.lblPrices) );

        // Build list with prices
        for(int i = 0; i < BurguerConfigurator.getNumIngredients(); ++i) {
            desc.append( String.format( Locale.getDefault(), "%4.2f€ %s",
                            BurguerConfigurator.COSTS[ i ],
                            BurguerConfigurator.INGREDIENTS[ i ] ) );
            desc.append( '\n' );
        }

        lblData.setText( desc.toString() );
        lblData.setPadding( 10, 10, 10, 10 );
        dlg.setView( lblData );
        dlg.setPositiveButton( "Ok", null );
        dlg.create().show();
    }

    private BurguerConfigurator cfgBurguer;
}
