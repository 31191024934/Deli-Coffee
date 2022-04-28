package project.edu.example.delicoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingIn extends AppCompatActivity {
   private Button btnnext;
private EditText edtemail,edtpassword;
private TextView singup;
private LinearLayout forgot_password;
ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);


        initUi();
        initListenerr();

    }
    private void initUi()
    {
        edtemail= findViewById(R.id.singIn_edt_email);
        edtpassword= findViewById(R.id.singIn_edt_password);
        singup= findViewById(R.id.singIn_singup);
        btnnext=(Button) findViewById(R.id.signIn_btn_Next);

        progressDialog = new ProgressDialog(this);
        forgot_password= findViewById(R.id.signin_forgot_password);

    }
    private void initListenerr()
    {
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignIn();
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickForGotPassWord();
            }
        });
    }

    private void onClickSignIn() {
        String stremail = edtemail.getText().toString().trim();
        String strpassword = edtpassword.getText().toString().trim();
        if(stremail.isEmpty()){
            edtemail.setError("Bạn chưa nhập email!");
            edtemail.requestFocus();
            return;
        }
        if(strpassword.isEmpty()){
            edtpassword.setError("Bạn chưa nhập mật khẩu!");
            edtpassword.requestFocus();
            return;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        progressDialog.show();
        auth.signInWithEmailAndPassword(stremail, strpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) { // login thanh cong
                          Intent intent = new Intent(SingIn.this,Home.class);
                          startActivity(intent);
                        } else {
                            // logon that bại
                            Toast.makeText(SingIn.this, "Login failed",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private   void onClickForGotPassWord()
    {
      Intent intent = new Intent (SingIn.this,Forgot_Password.class);
      startActivity(intent);

    }
    private void onClickSignUp()
    {
        Intent intent = new Intent(getApplicationContext(),SignUp.class);
        startActivity(intent);
    }


}