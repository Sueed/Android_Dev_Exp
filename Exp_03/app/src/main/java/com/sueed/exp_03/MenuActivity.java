package com.sueed.exp_03;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity
{
    TextView mu_test;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //setUp
        mu_test = findViewById(R.id.mu_test);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.font_small:
                smallFont();
                break;
            case R.id.font_mid:
                midFont();
                break;
            case R.id.font_big:
                bigFont();
                break;
            case R.id.mi_normal:
                toast();
                break;
            case R.id.font_red:
                red();
                break;
            case R.id.font_black:
                black();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //link menu
    @Override
    public boolean onCreateOptionsMenu(Menu m)
    {
        this.getMenuInflater().inflate(R.menu.menu_demo, m);

        return true;
    }

    //small font size
    public void smallFont()
    {
        mu_test.setTextSize(10);
    }

    //middle font size
    public void midFont()
    {
        mu_test.setTextSize(16);
    }

    //big font size
    public void bigFont()
    {
        mu_test.setTextSize(20);
    }

    //simple menu item
    public void toast()
    {
        Toast toast = Toast.makeText(this, "这是普通菜单项", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void red()
    {
        mu_test.setTextColor(Color.RED);
    }

    public void black()
    {
        mu_test.setTextColor(Color.BLACK);
    }

    //点击进入下一页
    public void click_to_next(View V)
    {
        Intent next = new Intent();
        next.setClass(this, ActionModeActivity.class);
        startActivity(next);
    }
}
