package com.ezekial.ejuicecalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        calculateResults();
        setupButton();
    }

    private void calculateResults() {
        TextView txt = (TextView) findViewById(R.id.results);
        try{
            Intent i = getIntent();
            Double baseNic = i.getDoubleExtra("baseNic", 0.0);
            Double targetNic = i.getDoubleExtra("targetNic", 0.0);
            Double totalQuantity = i.getDoubleExtra("totalQuantity", 0.0);
            Double flavourPercent = i.getDoubleExtra("flavourPercent", 0.0);
            Double otherPercent = i.getDoubleExtra("otherPercent", 0.0);

            Double newBase = totalQuantity / (baseNic / targetNic);
            Double mlFlavour = totalQuantity * (flavourPercent / 100.0);
            Double mlOther = totalQuantity * (otherPercent / 100.0);
            Double tooFillWith = totalQuantity - newBase - mlFlavour - mlOther;

            DecimalFormat f = new DecimalFormat("##.00");

            txt.setText(String.format("Base with Nicotine: %sml\n" +
                    "Base without Nicotine: %sml\n" +
                    "Flavour: %sml\n" +
                    "Other: %sml\n" +
                    "To make %sml of eJuice!",
                    f.format(newBase), f.format(tooFillWith),
                    f.format(mlFlavour), f.format(mlOther),
                    f.format(totalQuantity)));
        } catch(Exception e){
            txt.setText(e.toString());
        }
    }

    private void setupButton() {
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (R.id.action_settings == id) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
