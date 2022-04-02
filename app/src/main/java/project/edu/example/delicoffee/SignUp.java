package project.edu.example.delicoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
ImageView imgPrevious;
Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }
    protected void init()
    {
        imgPrevious=(ImageView) findViewById(R.id.imgPrevious);
        btnNext=(Button)findViewById(R.id.btnNext);
        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SingIn.class);
                startActivity(intent);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUp.this,"Đăng kí thành công ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),SingIn.class);
                startActivity(intent);
            }
        });
    }
}