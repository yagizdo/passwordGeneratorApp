package com.example.passwordgeneratorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SifrelerAdapter extends RecyclerView.Adapter<SifrelerAdapter.CardTasarımTutucu>{
    private Context mContext;
    private List<Sifreler> sifrelerList;
    private Veritabani vt;

    public SifrelerAdapter(Context mContext, List<Sifreler> sifrelerList, Veritabani vt) {
        this.mContext = mContext;
        this.sifrelerList = sifrelerList;
        this.vt = vt;
    }

    @NonNull
    @Override
    public CardTasarımTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sifre_card_tasarim,parent,false);
        return new CardTasarımTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarımTutucu holder, int position) {
      final Sifreler sifre = sifrelerList.get(position);

        holder.textViewKisiBilgi.setText(sifre.getSifre());

        holder.ayarlarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext,holder.ayarlarImg);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_sil:
                                Snackbar.make(holder.ayarlarImg,"Şifre silinsin mi?",Snackbar.LENGTH_LONG)
                                        .setAction("Evet", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                new SifrelerDao().sifreSil(vt,sifre.getSifre_id());
                                                Toast.makeText(mContext, "Şifre Silindi", Toast.LENGTH_SHORT).show();
                                                sifrelerList = new SifrelerDao().tumSifreler(vt);
                                                notifyDataSetChanged();
                                            }
                                        }).show();

                                return true;
                            default: return false;
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return sifrelerList.size();
    }


    public class CardTasarımTutucu extends RecyclerView.ViewHolder{
        private TextView textViewKisiBilgi;
        private ImageView ayarlarImg;

        public CardTasarımTutucu(@NonNull View itemView) {
            super(itemView);

            textViewKisiBilgi = itemView.findViewById(R.id.textViewCardSifre);
            ayarlarImg = itemView.findViewById(R.id.imageViewCardMore);
        }
    }
}
