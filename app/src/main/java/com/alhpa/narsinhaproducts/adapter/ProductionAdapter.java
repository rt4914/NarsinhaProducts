package com.alhpa.narsinhaproducts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.activity.AddProductionActivity;
import com.alhpa.narsinhaproducts.table.MainUnitTable;
import com.alhpa.narsinhaproducts.table.ProductionTable;
import com.alhpa.narsinhaproducts.utility.UtilityFile;

import java.util.List;

public class ProductionAdapter extends RecyclerView.Adapter<ProductionAdapter.MyViewHolder> {

    private Context context;
    private List<ProductionTable> productionTableList;

    private UtilityFile utilityFile;

    public ProductionAdapter(Context context, List<ProductionTable> productionTableList) {
        this.context = context;
        this.productionTableList = productionTableList;

        utilityFile = new UtilityFile(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_production_adapter, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvArticleName.setText("Article Name: " + productionTableList.get(i).getArticleName());
        myViewHolder.tvLotNumber.setText("Lot Number: " + productionTableList.get(i).getLotNumber());
        myViewHolder.tvStartDate.setText("Start Date: " + utilityFile.getStringFromLongTimestamp(productionTableList.get(i).getStartDate(), "dd MMM yyyy"));

        if ((i + 1) == productionTableList.size()) {
            myViewHolder.view.setVisibility(View.GONE);
        } else {
            myViewHolder.view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return productionTableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLotNumber;
        private TextView tvArticleName;
        private TextView tvStartDate;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLotNumber = itemView.findViewById(R.id.tvLotNumber);
            tvArticleName = itemView.findViewById(R.id.tvArticleName);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            view = itemView.findViewById(R.id.view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context, AddProductionActivity.class);
                    i.putExtra("LOT_NUMBER", productionTableList.get(getAdapterPosition()).getLotNumber());
                    i.putExtra("EDIT", true);
                    context.startActivity(i);

                }
            });

        }
    }

}
