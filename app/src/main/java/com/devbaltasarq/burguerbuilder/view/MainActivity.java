package com.devbaltasarq.burguerbuilder.view;

import android.content.DialogInterface;
import android.os.Debug;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devbaltasarq.burguerbuilder.R;
import com.devbaltasarq.burguerbuilder.core.BurguerSelector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the burguer configuration
        this.cfgBurguer = new BurguerSelector();

        // Report relevant info
        Log.i( "MainActivity.OnCreate", "Number of ingredients: "
                + BurguerSelector.INGREDIENTS.length );

        for(int i = 0; i < BurguerSelector.INGREDIENTS.length; ++i) {
            Log.i( "MainActivity.OnCreate", "Availabel ingredient: "
                + BurguerSelector.INGREDIENTS[ i ] );
        }

        Button btIngredients = (Button) this.findViewById( R.id.btIngredients );
        btIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.showIngredientsDialog();
            }
        });
    }

    private void showIngredientsDialog()
    {
        final boolean[] selections = this.cfgBurguer.getSelected();
        final TextView lblTotal = (TextView) this.findViewById( R.id.lblTotal );
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        dlg.setTitle( "Ingredients" );

        dlg.setMultiChoiceItems(
                BurguerSelector.INGREDIENTS,
                selections,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        selections[ i ] = b;
                    }
                }
        );

        dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                lblTotal.setText( Double.toString( MainActivity.this.cfgBurguer.getTotalCost() ) );
            }
        });
        dlg.create().show();
    }

    private BurguerSelector cfgBurguer;
}
