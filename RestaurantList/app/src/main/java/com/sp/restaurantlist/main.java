package com.sp.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;
import android.R.layout;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    ListView list;
    ArrayAdapter arr=null;
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
        arr=new ArrayAdapter(this, layout.simple_list_item_1, restaurants);
        list.setAdapter(arr);
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
                arr.add(res);
            }
        });
    }
}