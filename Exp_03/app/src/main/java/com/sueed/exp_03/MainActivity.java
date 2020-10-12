package com.sueed.exp_03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //user_name
    private String[] names = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};

    private int[] imgs =
            {R.drawable.lion,
                    R.drawable.tiger,
                    R.drawable.monkey,
                    R.drawable.dog,
                    R.drawable.cat,
                    R.drawable.elephant};

    HashMap<View, Boolean> view_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_select = new HashMap<>();

        //loading name list
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("Name", names[i]);
            listItem.put("Img", imgs[i]);
            listItems.add(listItem);
        }

        //create a SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.list_unit_01, new String[]{"Name", "Img"}, new int[]{R.id.list_unit_01_name, R.id.list_unit_01_img});
        ListView list = findViewById(R.id.LV_demo);

        //set Adapter for ListView
        list.setAdapter(simpleAdapter);
    }

    //click_to_select block
    public void click_select (View V) throws Exception{
        LinearLayout list_unit = V.findViewById(R.id.list_unit_01);
        TextView name = V.findViewById(R.id.list_unit_01_name);
            if (view_select.get(V) == null || !view_select.get(V)) {
                list_unit.setBackgroundColor(Color.BLUE);
                view_select.put(V, true);
                Toast toast = Toast.makeText(MainActivity.this, name.getText(), Toast.LENGTH_SHORT);
                toast.show();
            } else {
                list_unit.setBackgroundColor(Color.WHITE);
                view_select.put(V, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(View.inflate(this, R.layout.alert, null));
                builder.show();
            }
    }

    //click_input_text
//    public void click_input_text (View V){
//        //EditText editText = V.findViewWithTag();
//    }

    //click_to_cancel
    public void click_to_cancel (View V){
//        AlertDialog alertDialog = new AlertDialog(findViewById(R.id.alert));
//        alertDialog.hide();
    }

    //click_to_next
    public void click_to_next(View V)
    {
        Intent next = new Intent();
        next.setClass(this, MenuActivity.class);
        startActivity(next);
    }
}