package com.alhpa.narsinhaproducts.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.adapter.ProductionAdapter;
import com.alhpa.narsinhaproducts.adapter.VariationAdapter;
import com.alhpa.narsinhaproducts.table.MainUnitTable;
import com.alhpa.narsinhaproducts.table.ProductionTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class ProductionMainActivity extends AppCompatActivity {

    private List<ProductionTable> productionTableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        showRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.action_add) {

            Intent i = new Intent(this, AddProductionActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);

    }

    private void showRecyclerView(){

        productionTableList = AppDatabase.getInstance(this).productionTableDao().getAllProductionByEndDateNonLive();

        RecyclerView rvMainUnit = findViewById(R.id.rvMainUnit);
        rvMainUnit.setLayoutManager(new LinearLayoutManager(this));

        if (productionTableList != null && !productionTableList.isEmpty()) {
            rvMainUnit.setVisibility(View.VISIBLE);
            ProductionAdapter adapter = new ProductionAdapter(this, productionTableList);
            rvMainUnit.setAdapter(adapter);
        } else {
            rvMainUnit.setVisibility(View.GONE);
        }

    }

}
