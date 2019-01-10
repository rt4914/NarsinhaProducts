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
import com.alhpa.narsinhaproducts.activity.StockVariationActivity;
import com.alhpa.narsinhaproducts.table.MainUnitTable;
import com.alhpa.narsinhaproducts.utility.UtilityFile;

import java.util.HashMap;
import java.util.List;

public class StockMainAdapter extends RecyclerView.Adapter<StockMainAdapter.MyViewHolder> {

    private Context context;
    private List<String> articleNameList;
    private List<Integer> articleRemainingQuantityList;

    private UtilityFile utilityFile;

    public StockMainAdapter(Context context, List<String> articleNameList, List<Integer> articleRemainingQuantityList) {
        this.context = context;
        this.articleNameList = articleNameList;
        this.articleRemainingQuantityList = articleRemainingQuantityList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_stock_main, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvArticleName.setText("Article Name: " + articleNameList.get(i));
        myViewHolder.tvRemainingQuantity.setText("" + articleRemainingQuantityList.get(i));

        if ((i + 1) == articleRemainingQuantityList.size()) {
            myViewHolder.view.setVisibility(View.GONE);
        } else {
            myViewHolder.view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return articleRemainingQuantityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRemainingQuantity;
        private TextView tvArticleName;

        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRemainingQuantity = itemView.findViewById(R.id.tvRemainingQuantity);
            tvArticleName = itemView.findViewById(R.id.tvArticleName);
            view = itemView.findViewById(R.id.view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context, StockVariationActivity.class);
                    i.putExtra("ARTICLE_NAME", articleNameList.get(getAdapterPosition()));
                    context.startActivity(i);

                }
            });

        }
    }

}
