package project.edu.example.delicoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
         private    EditText edt_email;
         private Button btn_resetpassword;
         private ImageView img_previous;
         ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initUi();
        initListener();
    }

    private void initUi() {
        edt_email= findViewById(R.id.forgotpassword_edt_email);
        btn_resetpassword= findViewById(R.id.forgotpassword_btn_resetpassword);
        img_previous=findViewById(R.id.forgotpassword_img_previous);
        progressDialog=new ProgressDialog(this);
    }
    private void initListener()
    { img_previous.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent (Forgot_Password.this,SingIn.class);
            startActivity(intent);
        }
    });
        btn_resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickReset();
            }
        });

    }
    private void onClickReset()
    {
        progressDialog.show();
        String stremail= edt_email.getText().toString().trim();
        if(stremail.isEmpty()){
            edt_email.setError("Bạn chưa nhập email!");
            edt_email.requestFocus();
            return;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = stremail;

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Forgot_Password.this,"Email has been sent, please check",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent (Forgot_Password.this,SingIn.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Forgot_Password.this,
                                    "This email is not registered",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}