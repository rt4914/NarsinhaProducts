package com.alhpa.narsinhaproducts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alhpa.narsinhaproducts.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void colorCardClicked(View view) {

        Intent i = new Intent(this, AddColorActivity.class);
        startActivity(i);

    }

    public void productionCardClicked(View view) {

        Intent i = new Intent(this, ProductionMainActivity.class);
        startActivity(i);

    }

    public void stockCardClicked(View view) {

        Intent i = new Intent(this, StockMainActivity.class);
        startActivity(i);

    }

    public void dispatchCardClicked(View view) {

        Intent i = new Intent(this, DispatchMainActivity.class);
        startActivity(i);

    }

    public void articleCardClicked(View view) {

        Intent i = new Intent(this, AddArticleNameActivity.class);
        startActivity(i);

    }

}
