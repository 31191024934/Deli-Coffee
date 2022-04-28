package project.edu.example.delicoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUi();

        initListener();
    }
private void initUi()
{
    btnadd= findViewById(R.id.home_add);
}
        private void initListener()
        {
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkLogin();
                }
            });
        }
    private void checkLogin()
    { // kiemr tra login hay chưa
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            // chưa login
            Intent intent = new Intent(this,SingIn.class);
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(this,Profile_S.class);
            startActivity(intent);
        }
        finish();
    }
}
