package com.sp.restaurantlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.R.layout;
import android.content.Context;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

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
    TabHost host;
    ListView list;
    Cursor model=null;
    RestaurantHelper helper=null;

    boolean showmenu=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        save=findViewById(R.id.button_save);
        name=findViewById(R.id.restaurant_name);
        addr=findViewById(R.id.restaurant_address);
        number=findViewById(R.id.restaurant_tel);
        rg=findViewById(R.id.restaurant_types);
        list = findViewById(R.id.restaurants);
        helper=new RestaurantHelper(this);
        model= helper.getAll();
        adapter=new RestaurantAdapter(this, model, 0);
        list.setAdapter(adapter);
        list.setOnItemClickListener(onic);
        host=findViewById(R.id.thost);
        host.setup();
        TabHost.TabSpec spec=host.newTabSpec("List");
        spec.setContent(R.id.restaurantsTab);
        spec.setIndicator("List");
        host.addTab(spec);

        spec=host.newTabSpec("Details");
        spec.setContent(R.id.detailsTab);
        spec.setIndicator("Details");
        host.addTab(spec);
        host.setCurrentTab(1);
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String TabId){
                invalidateOptionsMenu();
            }
        });


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
//                Restaurant res =  new Restaurant();
//                res.setAddress(a);
//                res.setName(n);
//                res.setTelephone(num);
//                res.setRestaurantType(RType);
//                restaurants.add(res);
                helper.insert(n,a,num,RType);
                model=helper.getAll();
                adapter.swapCursor(model);
                host.setCurrentTab(0);
            }
        });
        super.onCreate(savedInstanceState);
    }

    @Override
    public void invalidateOptionsMenu() {
        //Log.i("currenttab", host.getCurrentTab().To);
        if (host.getCurrentTab()==0){
            showmenu=false;
        }else{
            showmenu=true;
        }
        super.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (showmenu){
            getMenuInflater().inflate(R.menu.option, menu);
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onStart() {
        invalidateOptionsMenu();
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.about){
            Toast.makeText(this, "Restaurant List version 1.0", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
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

        void populateFrom(Cursor c, RestaurantHelper h) {
            restName.setText(h.getRestaurantName(c));
            addr.setText(h.getRestaurantAddress(c)+", "+h.getRestaurantTel(c));
            if (h.getRestaurantType(c).equals("Chinese")) {
                icon.setImageResource(R.drawable.ball_red);
            } else if (h.getRestaurantType(c).equals("Western")) {
                icon.setImageResource(R.drawable.ball_yellow);
            } else {
                icon.setImageResource(R.drawable.ball_green);
            }
        }
    }

    class RestaurantAdapter extends CursorAdapter {
        RestaurantAdapter(Context context, Cursor cursor, int flags) {
            super(context, cursor, flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            RestaurantHolder holder = (RestaurantHolder) view.getTag();
            holder.populateFrom(cursor, helper);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            RestaurantHolder holder = new RestaurantHolder(row);
            row.setTag(holder);
            return (row);
        }


    }
    AdapterView.OnItemClickListener onic=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            model.moveToPosition(position);
            name.setText(helper.getRestaurantName(model));
            addr.setText(helper.getRestaurantAddress(model));
            number.setText(helper.getRestaurantTel(model));
            String sel=helper.getRestaurantType(model);
            Log.i("rg", sel);
            switch (sel){
                case "Chinese":
                    rg.check(R.id.Chinese);
                    break;
                case "Western":
                    rg.check(R.id.Western);
                    break;
                case "Indian":
                    rg.check(R.id.Indian);
                    break;
                case "Indonesia":
                    rg.check(R.id.Indonesia);
                    break;
                case "Korean":
                    rg.check(R.id.Korean);
                    break;
                case "Japanese":
                    rg.check(R.id.Japanese);
                    break;
                case "Thai":
                    rg.check(R.id.Thai);
                    break;
                default:

            }
            host.setCurrentTab(1);
        }
    };




}

