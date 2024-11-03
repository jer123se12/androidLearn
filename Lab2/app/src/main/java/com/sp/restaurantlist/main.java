package com.sp.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;
import android.R.layout;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity {
    EditText name;
    EditText addr;
    EditText number;
    Button save;
    RadioGroup rg;
    List<Restaurant> restaurants=new ArrayList<>();
    private RestaurantAdapter adapter=null;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=findViewById(R.id.button_save);
        name=findViewById(R.id.restaurant_name);
        addr=findViewById(R.id.restaurant_address);
        number=findViewById(R.id.restaurant_tel);
        rg=findViewById(R.id.restaurant_types);
        list = findViewById(R.id.restaurants);
        adapter=new RestaurantAdapter();
        list.setAdapter(adapter);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n, a, num;
                String RType="";
                n=name.getText().toString();
                a=addr.getText().toString();
                num=number.getText().toString();
                RType=((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                Toast.makeText(getApplicationContext(), n+"\n"+a+"\n"+num+"\n"+RType,Toast.LENGTH_LONG).show();
                Restaurant res =  new Restaurant();
                res.setAddress(a);
                res.setName(n);
                res.setTelephone(num);
                res.setRestaurantType(RType);
//                restaurants.add(res);
                adapter.add(res);
            }
        });
    }
    static class RestaurantHolder {
        private TextView restName = null;
        private TextView addr = null;
        private ImageView icon = null;

        RestaurantHolder(View row) {
            restName = row.findViewById(R.id.restName);
            addr = row.findViewById(R.id.restAddr);
            icon = row.findViewById(R.id.icon);
        }

        void populateFrom(Restaurant r) {
            restName.setText(r.getName());
            addr.setText(r.getAddress());
            if (r.getRestaurantType().equals("Chinese")) {
                icon.setImageResource(R.drawable.ball_red);
            } else if (r.getRestaurantType().equals("Western")) {
                icon.setImageResource(R.drawable.ball_yellow);
            } else {
                icon.setImageResource(R.drawable.ball_green);
            }
        }
    }

    class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        RestaurantAdapter() { super(main.this, R.layout.row, restaurants); }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            RestaurantHolder holder;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
                holder = new RestaurantHolder(row);
                row.setTag(holder);
            } else {
                holder = (RestaurantHolder) row.getTag();
            }
            holder.populateFrom(restaurants.get(position));
            return row;
        }
    }
}