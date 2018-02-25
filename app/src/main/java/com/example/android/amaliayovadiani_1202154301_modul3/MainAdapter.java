package com.example.android.amaliayovadiani_1202154301_modul3;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Asus on 24/02/2018.
 */

public class MainAdapter extends RecyclerView.Adapter <MainAdapter.holdermn> {

    private Context context;
    private List<menulist> daftarmenu;

    //adpater yang akan menyiapkan layout untuk daftar menu
    public MainAdapter (Context context, List<menulist> daftarmenu) {
        this.context = context;
        this.daftarmenu = daftarmenu;
    }

    @Override
    //membuat suatu tampilan dan mengembalikannya
    public holdermn onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View vw = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        holdermn hldr = new holdermn(vw);
        return hldr;
    }

    @Override
    //menghubungkan data dengan view holder pada posisi yang ditentukan dalam RecyclerView
    public void onBindViewHolder(holdermn holder, int position) {
        //mengambil elemen dari array pada posisi tertentu
        menulist dt = daftarmenu.get(position);
        //mengeset isi view dengan elemen dari data
        holder.nama.setText(dt.getNama());
        holder.detail.setText(dt.getDesc());
        //kondisi gambar dari drawable dijadikan sebagai background dari cardview
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            holder.rl.setBackground(holder.rl.getResources().getDrawable(dt.getGambar()));
        }
        holder.rl.setTag(dt.getGambar());
    }

    @Override
    //menghitung ukuran dataset/jumlah data yang akan ditampilkan
    public int getItemCount() {
        return daftarmenu.size();
    }

    class holdermn extends RecyclerView.ViewHolder{
        //deklarasi variable
        TextView nama, detail;
        RelativeLayout rl;
        public holdermn(View itemView) {
            super(itemView);
            //mengakses nama, detail, dan rl yang ada pada layout
            nama = itemView.findViewById(R.id.namaAir);
            detail = itemView.findViewById(R.id.detailAir);
            rl = itemView.findViewById(R.id.rl);
            //implementasi onclicklistener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                //ketika cardview di klik
                public void onClick(View view) {
                    //bmembuat intent bernama move
                    Intent move = new Intent(context, detailactivity.class);
                    //mengirimkan data data yang diambil ke detail air untuk di tampilkan
                    move.putExtra("nama", nama.getText());
                    move.putExtra("detail", detail.getText());
                    move.putExtra("gambar",rl.getTag().toString());
                    //mengeksekusi intent
                    context.startActivity(move);
                }
            });
        }
    }
}