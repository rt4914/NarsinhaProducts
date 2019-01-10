package com.alhpa.narsinhaproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.activity.AddColorActivity;
import com.alhpa.narsinhaproducts.table.ColorTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHolder> {

    private Context context;
    private List<ColorTable> colorTableList;
    private boolean isSelectMode;

    public ColorAdapter(Context context, List<ColorTable> colorTableList, boolean isSelectMode) {
        this.context = context;
        this.colorTableList = colorTableList;
        this.isSelectMode = isSelectMode;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_color, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvColor.setText(colorTableList.get(i).getColorName());

        if ((i + 1) == colorTableList.size()) {
            myViewHolder.view.setVisibility(View.GONE);
        } else {
            myViewHolder.view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return colorTableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivDeleteColor;
        private TextView tvColor;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivDeleteColor = itemView.findViewById(R.id.ivDeleteColor);
            tvColor = itemView.findViewById(R.id.tvColor);
            view = itemView.findViewById(R.id.view);

            if(isSelectMode){
                ivDeleteColor.setVisibility(View.GONE);
                itemView.setClickable(true);
            }
            else{
                ivDeleteColor.setVisibility(View.VISIBLE);
                itemView.setClickable(false);
            }

            ivDeleteColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AppDatabase.getInstance(context).colorTableDao().deleteByName(colorTableList.get(getAdapterPosition()).getColorName());

                    Toast.makeText(context, "Color Deleted", Toast.LENGTH_SHORT).show();

                    colorTableList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ((AddColorActivity)context).colorSelected(colorTableList.get(getAdapterPosition()).getColorName());

                }
            });

        }
    }

}
