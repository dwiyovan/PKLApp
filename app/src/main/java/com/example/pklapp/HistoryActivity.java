package com.example.pklapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HistoryActivity extends Fragment {
    TextView status,id,tgl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_history,container,false);

        status=view.findViewById(R.id.status_pesanan_history);
        id=view.findViewById(R.id.id_transaksi);
        tgl=view.findViewById(R.id.tanggal_pesan);

        SharedPreferences sharedpref= PreferenceManager.getDefaultSharedPreferences(getContext());
        String statuspesan=sharedpref.getString("statuspesan","id_status");
        String statuspesanid=sharedpref.getString("id_statuspesan","");
        String tglpesan=sharedpref.getString("tglpesan","");

        status.setText("Status pesanan   : "+"\n"+statuspesan);
        id.setText    ("ID Transaksi     : "+"\n"+statuspesanid);
        tgl.setText   ("Tanggal pemesanan: "+"\n"+tglpesan);
        Log.d("testing", "ini status:  "+statuspesan+"ini id:"+statuspesanid);

        return view;

    }
}
