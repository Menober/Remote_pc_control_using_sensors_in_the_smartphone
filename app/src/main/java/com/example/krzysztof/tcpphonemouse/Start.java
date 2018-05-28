package com.example.krzysztof.tcpphonemouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void start(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        MainActivity.IP=((EditText)findViewById(R.id.ipField)).getText().toString();
        MainActivity.PORT=Integer.valueOf(((EditText)findViewById(R.id.portField)).getText().toString());
        finish();
    }
}
