package com.alhpa.narsinhaproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.table.DispatchUnitTable;
import com.alhpa.narsinhaproducts.utility.UtilityFile;

import java.util.List;

public class DispatchAdapter extends RecyclerView.Adapter<DispatchAdapter.MyViewHolder> {

    private Context context;
    private List<DispatchUnitTable> dispatchUnitTableList;

    private UtilityFile utilityFile;

    public DispatchAdapter(Context context, List<DispatchUnitTable> dispatchUnitTableList) {
        this.context = context;
        this.dispatchUnitTableList = dispatchUnitTableList;

        utilityFile = new UtilityFile(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dispatch_adapter, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvChallanNumber.setText("Challan Number: " + dispatchUnitTableList.get(i).getChallanNumber());
        myViewHolder.tvDate.setText("Date: " + utilityFile.getStringFromLongTimestamp(dispatchUnitTableList.get(i).getDate(), "dd MMM yyyy"));
        myViewHolder.tvPartyName.setText("Party Name: " + dispatchUnitTableList.get(i).getPartyName());

        if ((i + 1) == dispatchUnitTableList.size()) {
            myViewHolder.view.setVisibility(View.GONE);
        } else {
            myViewHolder.view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return dispatchUnitTableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvChallanNumber;
        private TextView tvDate;
        private TextView tvPartyName;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChallanNumber = itemView.findViewById(R.id.tvChallanNumber);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPartyName = itemView.findViewById(R.id.tvPartyName);
            view = itemView.findViewById(R.id.view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Intent i = new Intent(context, AddProductionActivity.class);
//                    i.putExtra("CHALLAN_NUMBER", dispatchUnitTableList.get(getAdapterPosition()).getChallanNumber());
//                    i.putExtra("EDIT", true);
//                    context.startActivity(i);

                }
            });

        }
    }

}
