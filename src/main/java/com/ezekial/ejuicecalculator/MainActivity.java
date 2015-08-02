package com.ezekial.ejuicecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButton();
        setupToasts();
    }

    private void setupToasts() {
        TextView baseNicTxt = (TextView) findViewById(R.id.baseNicTxt);
        TextView targetNicTxt = (TextView) findViewById(R.id.targetNicTxt);
        TextView totalQuantityTxt = (TextView) findViewById(R.id.totalQuantityTxt);
        TextView flavourPercentTxt = (TextView) findViewById(R.id.flavourPercentTxt);
        TextView otherPercentTxt = (TextView) findViewById(R.id.otherPercentTxt);

        baseNicTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "How much nicotine per ml do you have?", Toast.LENGTH_LONG).show();
            }
        });

        targetNicTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "How much nicotine per ml do you want?", Toast.LENGTH_LONG).show();
            }
        });

        totalQuantityTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "How many ml are you making?", Toast.LENGTH_LONG).show();
            }
        });

        flavourPercentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "What percentage will be flavouring?", Toast.LENGTH_LONG).show();
            }
        });

        otherPercentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Adding water?", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void setupButton(){
        Button calculate = (Button) findViewById(R.id.submitButton);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ready = true;
                Intent mIntent = new Intent(MainActivity.this, ResultActivity.class);

                EditText baseNic = (EditText) findViewById(R.id.baseNic);
                EditText targetNic = (EditText) findViewById(R.id.targetNic);
                EditText totalQuantity = (EditText) findViewById(R.id.quantity);
                EditText flavorPercent = (EditText) findViewById(R.id.flavor);
                EditText otherPercent = (EditText) findViewById(R.id.other);

                //check baseNic
                if(baseNic.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Missing Base Nicotine Field", Toast.LENGTH_LONG).show();
                    ready = false;
                } else {
                    mIntent.putExtra("baseNic", Double.parseDouble(baseNic.getText().toString())* 1.0);

                    //if ok check targetNic
                    if(targetNic.getText().toString().equals("")){
                        Toast.makeText(MainActivity.this, "Missing Target Nicotine Field", Toast.LENGTH_LONG).show();
                        ready = false;
                    } else {
                        mIntent.putExtra("targetNic", Double.parseDouble(targetNic.getText().toString())* 1.0);

                        //check to see if target is higher then base
                        if(Double.parseDouble(targetNic.getText().toString())* 1.0 > Double.parseDouble(baseNic.getText().toString())* 1.0){
                            Toast.makeText(MainActivity.this, "Target cannot be higher then base", Toast.LENGTH_LONG).show();
                            ready = false;
                        } else {

                            //if ok then check total
                            if(totalQuantity.getText().toString().equals("")){
                                Toast.makeText(MainActivity.this, "Missing Total Quantity Field", Toast.LENGTH_LONG).show();
                                ready = false;
                            } else {

                                //if ok then check fill in the rest
                                mIntent.putExtra("totalQuantity", Double.parseDouble(totalQuantity.getText().toString())* 1.0);
                                if(flavorPercent.getText().toString().equals("")){
                                    mIntent.putExtra("flavourPercent", 0.0);
                                } else {
                                    mIntent.putExtra("flavourPercent", Double.parseDouble(flavorPercent.getText().toString())* 1.0);
                                }

                                if(otherPercent.getText().toString().equals("")){
                                    mIntent.putExtra("otherPercent", 0.0);
                                } else {
                                    mIntent.putExtra("otherPercent", Double.parseDouble(otherPercent.getText().toString())* 1.0);
                                }
                            }
                        }
                    }
                }

                if(ready){
                    startActivity(mIntent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
