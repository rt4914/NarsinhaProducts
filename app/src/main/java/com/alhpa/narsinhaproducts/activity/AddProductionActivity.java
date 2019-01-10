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
import com.alhpa.narsinhaproducts.table.ProductionTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;
import com.alhpa.narsinhaproducts.utility.MyDatePicker;
import com.alhpa.narsinhaproducts.utility.MyDatePickerInterface;
import com.alhpa.narsinhaproducts.utility.UtilityFile;

import java.util.List;

public class AddProductionActivity extends AppCompatActivity {

    private TextView tvSelectArticle;
    private EditText etLotNumber;
    private TextView tvStartDate;
    private TextView tvEndDate;

    private TextView tvSelectColor;

    private ProductionTable productionTable = null;
    private MyDatePicker myDatePicker = null;
    private UtilityFile utilityFile;

    private String sArticleName;
    private String sLotNumber;
    private long lStartTimestamp = 0;
    private long lEndTimestamp = 0;

    private String currentColor;

    private boolean isEdit = false;

    private List<ArticleTable> articleTableList;

    private List<ArticleDispatchTable> dispatchUnitTableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_production);

        utilityFile = new UtilityFile(this);

        Intent i = getIntent();

        if (i != null) {
            isEdit = i.getBooleanExtra("EDIT", false);
            String sLotNumber = i.getStringExtra("LOT_NUMBER");
            productionTable = AppDatabase.getInstance(this).productionTableDao().getAllProductionByProductionNameNonLive(sLotNumber);
        }

        tvSelectArticle = findViewById(R.id.tvSelectArticle);
        etLotNumber = findViewById(R.id.etLotNumber);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);

        tvSelectColor = findViewById(R.id.tvSelectColor);

        if (productionTable != null) {
            etLotNumber.setEnabled(false);
            tvSelectArticle.setEnabled(false);
            tvStartDate.setEnabled(false);
            tvStartDate.setClickable(false);
            tvEndDate.setVisibility(View.VISIBLE);

            sLotNumber = productionTable.getLotNumber();
            sArticleName = productionTable.getArticleName();
            lEndTimestamp = productionTable.getEndDate();
            lStartTimestamp = productionTable.getStartDate();

            if (lEndTimestamp > 0) {
                tvEndDate.setText("End Date: " + utilityFile.getStringFromLongTimestamp(productionTable.getEndDate(), "dd MMM yyyy"));
                tvEndDate.setEnabled(false);
                tvEndDate.setClickable(false);
            }

            etLotNumber.setText(productionTable.getLotNumber());
            tvSelectArticle.setText(productionTable.getArticleName());
            tvStartDate.setText("Start Date: " + utilityFile.getStringFromLongTimestamp(productionTable.getStartDate(), "dd MMM yyyy"));

            showRecyclerView();

        }

        datePickerSetup();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 1) {
            currentColor = data.getStringExtra("COLOR");
            tvSelectColor.setText(currentColor);
        } else if (resultCode == RESULT_OK && requestCode == 2) {
            sArticleName = data.getStringExtra("ARTICLE_NAME");
            tvSelectArticle.setText("Article Name: " + sArticleName);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    public void selectStartDateMethod(View view) {

        myDatePicker.showDatePicker("START_DATE");

    }

    public void selectEndDateMethod(View view) {

        myDatePicker.showDatePicker("END_DATE");

    }

    public void saveButtonMethod(View view) {

        sArticleName = tvSelectArticle.getText().toString().trim();

        sLotNumber = etLotNumber.getText().toString().trim();

        if (productionTable != null) {

            if (lEndTimestamp > 0) {

                productionTable.setEndDate(lEndTimestamp);
                AppDatabase.getInstance(this).productionTableDao().update(productionTable);

                Toast.makeText(this, "Details Updated", Toast.LENGTH_SHORT).show();

                tvEndDate.setEnabled(false);
                tvEndDate.setClickable(false);

            } else {
                Toast.makeText(this, "Select end date", Toast.LENGTH_SHORT).show();
            }

            return;
        }

        if (utilityFile.doesStringExist(sArticleName) && utilityFile.doesStringExist(sLotNumber) && lStartTimestamp > 0) {

            productionTable = new ProductionTable();
            productionTable.setArticleName(sArticleName);
            productionTable.setStartDate(lStartTimestamp);
            productionTable.setEndDate(0);
            productionTable.setLotNumber(sLotNumber);
            AppDatabase.getInstance(this).productionTableDao().insert(productionTable);

            Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show();

            etLotNumber.setEnabled(false);
            tvSelectArticle.setEnabled(false);
            tvStartDate.setEnabled(false);
            tvStartDate.setClickable(false);

        } else {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }

    }

    public void datePickerSetup() {

        MyDatePickerInterface myDatePickerInterface = new MyDatePickerInterface() {
            @Override
            public void getCalendarTime(long lSelectedTimestamp, String sRequestType) {

                if (sRequestType.matches("START_DATE")) {

                    lStartTimestamp = lSelectedTimestamp;
                    tvStartDate.setText("Start Date: " + utilityFile.getStringFromLongTimestamp(lSelectedTimestamp, "dd MMM yyyy"));

                } else if (sRequestType.matches("END_DATE")) {

                    lEndTimestamp = lSelectedTimestamp;
                    tvEndDate.setText("End Date: " + utilityFile.getStringFromLongTimestamp(lSelectedTimestamp, "dd MMM yyyy"));

                }

            }

        };

        myDatePicker = new MyDatePicker(this, myDatePickerInterface);

    }

    public void addArticleVariationMethod(View view) {

        if (productionTable == null) {
            Toast.makeText(this, "Please fill above details first", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText etQuantity = findViewById(R.id.etQuantity);

        String sQuantity = etQuantity.getText().toString().trim();

        if (!utilityFile.doesStringExist(currentColor)) {
            Toast.makeText(this, "Please select color", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!utilityFile.doesStringExist(sQuantity)) {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        int iQuantity = Integer.parseInt(sQuantity);

        ArticleTable articleTable = new ArticleTable();
        articleTable.setArticleName(sArticleName);
        articleTable.setColorName(currentColor);
        articleTable.setQuantityRemaining(iQuantity);

        AppDatabase.getInstance(this).articleTableDao().insert(articleTable);

        Toast.makeText(this, "Color and quantity added", Toast.LENGTH_SHORT).show();

        currentColor = "";

        showRecyclerView();

    }

    public void selectArticleMethod(View view) {

        Intent i = new Intent(this, AddArticleNameActivity.class);
        i.putExtra("SELECT_ARTICLE", true);
        startActivityForResult(i, 2);

    }

    public void selectColorMethod(View view) {

        Intent i = new Intent(this, AddColorActivity.class);
        i.putExtra("SELECT_MODE", true);
        startActivityForResult(i, 1);

    }


    private void showRecyclerView() {

        articleTableList = AppDatabase.getInstance(this).articleTableDao().getAllArticleByArticleNameNonLive(sArticleName);

        for (int i = 0; i < articleTableList.size(); i++) {

            String sColor = articleTableList.get(i).getColorName();

            dispatchUnitTableList = AppDatabase.getInstance(this).articleDispatchTableDao().getAllArticleDispatchByArticleDispatchNameAndColorNonLive(sArticleName, sColor);

            int iDispatchQuantity = 0;

            for (int j = 0; j < dispatchUnitTableList.size(); j++) {
                iDispatchQuantity = iDispatchQuantity + dispatchUnitTableList.get(j).getQuantityDispatched();
            }

            int iRemainingQuantity = articleTableList.get(i).getQuantityRemaining();
            articleTableList.get(i).setQuantityRemaining((iDispatchQuantity + iRemainingQuantity));
        }

        RecyclerView rvVariation = findViewById(R.id.rvVariation);
        rvVariation.setLayoutManager(new LinearLayoutManager(this));

        if (articleTableList != null && !articleTableList.isEmpty()) {
            rvVariation.setVisibility(View.VISIBLE);
            VariationAdapter adapter = new VariationAdapter(this, articleTableList, false);
            rvVariation.setAdapter(adapter);
        } else {
            rvVariation.setVisibility(View.GONE);
        }

    }

}
