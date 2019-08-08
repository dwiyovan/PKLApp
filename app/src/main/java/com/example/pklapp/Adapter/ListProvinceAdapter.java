package com.example.pklapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pklapp.Model.Province.Result;
import com.example.pklapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListProvinceAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<com.example.pklapp.Model.Province.Result> provincelist;
    private ArrayList<com.example.pklapp.Model.Province.Result> lokalisasi;

    public ListProvinceAdapter(Activity activity, List<com.example.pklapp.Model.Province.Result> provincelist) {
        this.activity = activity;
        this.provincelist = provincelist;

        lokalisasi = new ArrayList<com.example.pklapp.Model.Province.Result>();
        lokalisasi.addAll(provincelist);
    }



    @Override
    public int getCount() {
        return provincelist.size();
    }

    @Override
    public Object getItem(int address) {
        return provincelist.get(address);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater==null)
            layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            convertView=layoutInflater.inflate(R.layout.itemlist,null);

        TextView nama_item=(TextView)convertView.findViewById(R.id.nama_item);
        TextView id_item=(TextView)convertView.findViewById(R.id.detail_item);

        com.example.pklapp.Model.Province.Result rs=provincelist.get(position);

        nama_item.setText(rs.getProvince());
        id_item.setText(rs.getProvince_id());
        return convertView;
    }

    public void search(String keyword){

        keyword=keyword.toLowerCase();

        provincelist.clear();
        if(keyword.length()==0)
            provincelist.addAll(lokalisasi);
        else{
            for (Result lok:lokalisasi){
                if(lok.getProvince().toLowerCase().contains(keyword))
                    provincelist.add(lok);
                else {}
            }
        }
        notifyDataSetChanged();
    }

    public void setList(List<com.example.pklapp.Model.Province.Result> provincelist ){this.lokalisasi.addAll(provincelist);}
}
