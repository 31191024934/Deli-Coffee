package project.edu.example.delicoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingIn extends AppCompatActivity {
Button btnnext;
TextView tvforgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        init();

    }
    protected  void init()
    {
        btnnext=(Button) findViewById(R.id.btnNext);
        tvforgotpassword=(TextView)findViewById(R.id.tvforgotpassword);
        tvforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }
    private void themdl()
    {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user/message");

        myRef.setValue("Hello, World!");

    }

}