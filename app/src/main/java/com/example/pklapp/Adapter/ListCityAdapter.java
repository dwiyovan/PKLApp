package com.example.pklapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pklapp.Model.City.Result;
import com.example.pklapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListCityAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<Result> citylist;
    private ArrayList<Result> lokalisasi;

    public ListCityAdapter(Activity activity, List<Result> citylist) {
        this.activity = activity;
        this.citylist = citylist;

        lokalisasi = new ArrayList<Result>();
        lokalisasi.addAll(citylist);
    }



    @Override
    public int getCount() {
        return citylist.size();
    }

    @Override
    public Object getItem(int address) {
        return citylist.get(address);
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

        Result rs=citylist.get(position);

        nama_item.setText(rs.getCity_name());
        id_item.setText(rs.getCity_id());
        return convertView;
    }

    public void search(String keyword){

        keyword=keyword.toLowerCase();

        citylist.clear();
        if(keyword.length()==0)
            citylist.addAll(lokalisasi);
        else{
        for (Result lok:lokalisasi){
            if(lok.getCity_name().toLowerCase().contains(keyword))
                citylist.add(lok);
            else {}
            }
        }
        notifyDataSetChanged();
    }

    public void setList(List<Result> citylist ){this.lokalisasi.addAll(citylist);}
}
