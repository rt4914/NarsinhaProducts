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
import com.alhpa.narsinhaproducts.adapter.DispatchAdapter;
import com.alhpa.narsinhaproducts.table.DispatchUnitTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class DispatchMainActivity extends AppCompatActivity {

    private List<DispatchUnitTable> dispatchUnitTableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_main);

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

            Intent i = new Intent(this, AddDispatchActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);

    }

    private void showRecyclerView(){

        dispatchUnitTableList = AppDatabase.getInstance(this).dispatchUnitTableDao().getAllDispatchUnitNonLive();

        RecyclerView rvDispatchMain = findViewById(R.id.rvDispatchMain);
        rvDispatchMain.setLayoutManager(new LinearLayoutManager(this));

        if (dispatchUnitTableList != null && !dispatchUnitTableList.isEmpty()) {
            rvDispatchMain.setVisibility(View.VISIBLE);
            DispatchAdapter adapter = new DispatchAdapter(this, dispatchUnitTableList);
            rvDispatchMain.setAdapter(adapter);
        } else {
            rvDispatchMain.setVisibility(View.GONE);
        }

    }

}
