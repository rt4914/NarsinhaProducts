package com.alhpa.narsinhaproducts.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.adapter.ColorAdapter;
import com.alhpa.narsinhaproducts.table.ColorTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class AddColorActivity extends AppCompatActivity {

    private static final String TAG = AddColorActivity.class.getSimpleName();

    private boolean isSelectMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_color);

        Intent i = getIntent();

        if(i!=null){
            isSelectMode = i.getBooleanExtra("SELECT_MODE", false);
        }

        showRecyclerView();

    }

    private void showRecyclerView() {

        RecyclerView rvColor = findViewById(R.id.rvColor);
        rvColor.setLayoutManager(new LinearLayoutManager(this));

        List<ColorTable> colorTableList = AppDatabase.getInstance(this).colorTableDao().getAllColorNonLive();

        Log.d(TAG, "showRecyclerView: " + colorTableList.size());

        if (!colorTableList.isEmpty()) {
            rvColor.setVisibility(View.VISIBLE);
            ColorAdapter adapter = new ColorAdapter(this, colorTableList, isSelectMode);
            rvColor.setAdapter(adapter);
        } else {
            rvColor.setVisibility(View.GONE);
        }

    }

    public void addColorMethod(View view) {

        Log.d(TAG, "addColorMethod: ");

        EditText etColor = findViewById(R.id.etColor);

        String sColor = etColor.getText().toString().trim();

        if (sColor.length() > 0) {
            Toast.makeText(this, "Color added", Toast.LENGTH_SHORT).show();
            ColorTable colorTable = new ColorTable();
            colorTable.setColorName(sColor.toUpperCase());
            AppDatabase.getInstance(this).colorTableDao().insert(colorTable);
            showRecyclerView();
            etColor.setText("");
        } else {
            Toast.makeText(this, "Enter color name", Toast.LENGTH_SHORT).show();
        }

    }

    public void colorSelected(String colorName) {

        Intent i = new Intent();
        i.putExtra("COLOR", colorName);
        setResult(Activity.RESULT_OK, i);
        finish();

    }

}
