package com.sueed.exp_04_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = findViewById(R.id.URL_content);
        Button browse_bt = findViewById(R.id.browse_button);
        browse_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);

                String link = url.getText().toString();
                if (!link.startsWith("https://"))
                {
                    link = "https://" + link;
                }
                Uri uri = Uri.parse(link);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    public void OpenURL(View V)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        String link = url.getText().toString();
        if (!link.startsWith("https://"))
        {
            link = "https://" + link;
        }
        Uri uri = Uri.parse(link);
        intent.setData(uri);
        startActivity(intent);

    }
}