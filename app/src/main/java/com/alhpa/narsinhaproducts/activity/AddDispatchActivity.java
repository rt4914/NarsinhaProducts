package com.alhpa.narsinhaproducts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.adapter.VariationAdapter;
import com.alhpa.narsinhaproducts.table.ArticleDispatchTable;
import com.alhpa.narsinhaproducts.table.ArticleTable;
import com.alhpa.narsinhaproducts.table.DispatchUnitTable;
import com.alhpa.narsinhaproducts.table.MainUnitTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;
import com.alhpa.narsinhaproducts.utility.MyDatePicker;
import com.alhpa.narsinhaproducts.utility.MyDatePickerInterface;
import com.alhpa.narsinhaproducts.utility.UtilityFile;

import java.util.List;

public class AddDispatchActivity extends AppCompatActivity {

    private EditText etPartyName;
    private EditText etChallanNumber;
    private TextView tvDate;

    private TextView tvSelectArticleName;
    private TextView tvSelectColor;

    private DispatchUnitTable dispatchUnitTable = null;
    private MyDatePicker myDatePicker = null;
    private UtilityFile utilityFile;

    private String sPartyName;
    private String sChallanNumber;
    private long lTimestamp = 0;

    private String currentColor;
    private String sArticle;

    private boolean isEdit = false;

    private List<ArticleDispatchTable> articleDispatchTableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dispatch);

        utilityFile = new UtilityFile(this);

//        Intent i = getIntent();
//
//        if (i != null) {
//
//            isEdit = i.getBooleanExtra("EDIT", false);
//            String sChallanNumber = i.getStringExtra("CHALLAN_NUMBER");
//
//            dispatchUnitTable = AppDatabase.getInstance(this).dispatchUnitTableDao().getAllMainUnitByMainUnitNameNonLive(sChallanNumber);
//
//        }

        etPartyName = findViewById(R.id.etPartyName);
        etChallanNumber = findViewById(R.id.etChallanNumber);
        tvDate = findViewById(R.id.tvDate);

        tvSelectArticleName = findViewById(R.id.tvSelectArticleName);
        tvSelectColor = findViewById(R.id.tvSelectColor);

        if (dispatchUnitTable != null) {
            etChallanNumber.setEnabled(false);
            etPartyName.setEnabled(false);
            tvDate.setEnabled(false);
            tvDate.setClickable(false);

            sChallanNumber = dispatchUnitTable.getChallanNumber();
            sPartyName = dispatchUnitTable.getPartyName();
            lTimestamp = dispatchUnitTable.getDate();

            etChallanNumber.setText(dispatchUnitTable.getChallanNumber());
            etPartyName.setText(dispatchUnitTable.getPartyName());
            tvDate.setText("Date: " + utilityFile.getStringFromLongTimestamp(dispatchUnitTable.getDate(), "dd MMM yyyy"));

            showRecyclerView();

        }

        datePickerSetup();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 1) {
            currentColor = data.getStringExtra("COLOR");
            sArticle = data.getStringExtra("ARTICLE_NAME");

            tvSelectColor.setText(currentColor);
            tvSelectArticleName.setText(sArticle);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    public void selectDateMethod(View view) {

        myDatePicker.showDatePicker("DATE");

    }

    public void saveButtonMethod(View view) {

        sPartyName = etPartyName.getText().toString().trim();

        sChallanNumber = etChallanNumber.getText().toString().trim();

        if (utilityFile.doesStringExist(sPartyName) && utilityFile.doesStringExist(sChallanNumber) && lTimestamp > 0) {

            dispatchUnitTable = new DispatchUnitTable();
            dispatchUnitTable.setPartyName(sPartyName);
            dispatchUnitTable.setDate(lTimestamp);
            dispatchUnitTable.setChallanNumber(sChallanNumber);
            AppDatabase.getInstance(this).dispatchUnitTableDao().insert(dispatchUnitTable);

            Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show();

            etChallanNumber.setEnabled(false);
            etPartyName.setEnabled(false);
            tvDate.setEnabled(false);
            tvDate.setClickable(false);

        } else {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }

    }

    public void datePickerSetup() {

        MyDatePickerInterface myDatePickerInterface = new MyDatePickerInterface() {
            @Override
            public void getCalendarTime(long lSelectedTimestamp, String sRequestType) {

                if (sRequestType.matches("DATE")) {

                    lTimestamp = lSelectedTimestamp;
                    tvDate.setText("Date: " + utilityFile.getStringFromLongTimestamp(lSelectedTimestamp, "dd MMM yyyy"));

                }

            }

        };

        myDatePicker = new MyDatePicker(this, myDatePickerInterface);

    }

    public void addDispatchArticleVariationMethod(View view) {

        if (dispatchUnitTable == null) {
            Toast.makeText(this, "Please fill above details first", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText e = findViewById(R.id.etDispatchQuantity);

        String sQuantity = e.getText().toString().trim();

        if (!utilityFile.doesStringExist(currentColor)) {
            Toast.makeText(this, "Please select color", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!utilityFile.doesStringExist(sQuantity)) {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        int iQuantity = Integer.parseInt(sQuantity);

        ArticleDispatchTable articleDispatchTable = new ArticleDispatchTable();
        articleDispatchTable.setChallanNumber(sChallanNumber);
        articleDispatchTable.setArticleDispatchName(sArticle);
        articleDispatchTable.setColorName(currentColor);
        articleDispatchTable.setQuantityDispatched(iQuantity);

        ArticleTable articleTable =  AppDatabase.getInstance(this).articleTableDao().getAllArticleByArticleNameAndColorNonLive(sArticle, currentColor);
        int iTotal = articleTable.getQuantityRemaining();
        articleTable.setQuantityRemaining((iTotal - iQuantity));
        articleTable.setColorName(currentColor);

        AppDatabase.getInstance(this).articleTableDao().insert(articleTable);
        AppDatabase.getInstance(this).articleDispatchTableDao().insert(articleDispatchTable);

        Toast.makeText(this, "Dispatched", Toast.LENGTH_SHORT).show();

        currentColor = "";
        sArticle = "";
        tvSelectColor.setText("Select Article-Color");

        showRecyclerView();

    }

    public void selectArticleColorMethod(View view) {

        Intent i = new Intent(this, SelectArticleActivity.class);
        startActivityForResult(i, 1);

    }


    private void showRecyclerView() {

        articleDispatchTableList = AppDatabase.getInstance(this).articleDispatchTableDao().getAllArticleDispatchByChallanNumberNonLive(sChallanNumber);

        RecyclerView rvArticleDispatch = findViewById(R.id.rvArticleDispatch);
        rvArticleDispatch.setLayoutManager(new LinearLayoutManager(this));

        if (articleDispatchTableList != null && !articleDispatchTableList.isEmpty()) {
            rvArticleDispatch.setVisibility(View.VISIBLE);
            VariationAdapter adapter = new VariationAdapter(this, articleDispatchTableList);
            rvArticleDispatch.setAdapter(adapter);
        } else {
            rvArticleDispatch.setVisibility(View.GONE);
        }

    }

}
