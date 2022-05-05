package project.edu.example.delicoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import project.edu.example.delicoffee.R;

public class Cover extends AppCompatActivity {
ImageView imgCover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        init();
    }
    protected void init(){
        imgCover=(ImageView) findViewById(R.id.imgCover);
        imgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getApplicationContext(), SingIn.class);
                startActivity(intent);

            }
        });
    }
}