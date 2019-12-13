package com.example.asus.finalpbo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MahasiswaViewHolder> {
    ItemClickListener itemclick;
    List<penduduk> mdata;
    Context context;

    public adapter(List<penduduk> mdata, Context context) {
        this.mdata = mdata;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new adapter.MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.MahasiswaViewHolder mahasiswaViewHolder, int i) {
        final penduduk mhs= mdata.get(i);
        mahasiswaViewHolder.no.setText("No = "+mhs.getNo());
        mahasiswaViewHolder.gol.setText("Golongan ="+mhs.getGolongan());
        mahasiswaViewHolder.pakai.setText("Pemakaian ="+pakai(mhs));
        mahasiswaViewHolder.total.setText("Total Biaya ="+total(mhs));
    }

    private String pakai(penduduk mhs){
        return String.valueOf(mhs.getPakai());
    }

    private String total(penduduk mhs){
        return String.valueOf(mhs.getTotal());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView no,gol,pakai,total;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.No);
            gol=itemView.findViewById(R.id.Golongan);
            pakai=itemView.findViewById(R.id.pakai);
            total=itemView.findViewById(R.id.Total);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemclick!=null){
                itemclick.onItemClick(v,getAdapterPosition());
            }
        }
    }

    void setItemclick(ItemClickListener itemclick){
        this.itemclick= itemclick;
    }

    public interface ItemClickListener {
        void onItemClick(View view,int position);
    }
}
