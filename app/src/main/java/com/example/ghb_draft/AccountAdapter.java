package com.example.ghb_draft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class AccountAdapter extends ArrayAdapter <AccountClass> {
    private AccountClass[] accountList;
    private int image;


    public AccountAdapter(Context context, int image, AccountClass[] accountList) {
        super(context, image, accountList);
        this.accountList = accountList;
        this.image = image;
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View line;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        line = layoutInflater.inflate(image,null);

        TextView name = (TextView)line.findViewById(R.id.textView);
        TextView capital = (TextView)line.findViewById(R.id.textView2);
        ImageView flag = (ImageView)line.findViewById(R.id.imageView);

        AccountClass countries = accountList[position];
        name.setText(countries.getAccountType());
        capital.setText(countries.getAccountAmount());
        flag.setImageResource(countries.getPicture());

        return line;

    }


}
