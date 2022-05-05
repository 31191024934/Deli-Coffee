package project.edu.example.delicoffee.activity;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.model.Users;

public class SignUp extends AppCompatActivity {
  private   ImageView imgPrevious;
private EditText edtphone,edtemail,edtpassword,edtname;
private Button btnNext;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUi();
        initListener();
    }
    private void initUi()
    {
        edtphone= findViewById(R.id.signup_edtphone);
        edtemail= findViewById(R.id.signup_edtemail);
        edtpassword= findViewById(R.id.signup_edtpassword);
        btnNext= findViewById(R.id.signup_btnsignup);
        imgPrevious=findViewById(R.id.imgPrevious);
        edtname=findViewById(R.id.signup_name);
        progressDialog = new ProgressDialog(this);

    }
    private void initListener()
    {
        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickPrevious();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();

            }
        });

    }
    private void onClickPrevious()
    {
        Intent intent = new Intent(SignUp.this, SingIn.class);
        startActivity(intent);
    }
    private void onClickSignUp()
    {
        String name =edtname.getText().toString().trim();
        String email = edtemail.getText().toString().trim();
        String password = edtpassword.getText().toString().trim();
        String phone = edtphone.getText().toString().trim();
        if(name.isEmpty()){
            edtname.setError("Bạn chưa nhập tên");
            edtname.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            edtphone.setError("Bạn chưa nhập số điện thoại");
            edtphone.requestFocus();
            return;
        }
        if(email.isEmpty()){
            edtemail.setError("Email không được để trống");
            edtemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edtpassword.setError("Password không được để trống");
            edtpassword.requestFocus();
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Users user = new Users(name,email,phone);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp.this,"Đăng ký thành công", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignUp.this,SingIn.class);
                                        startActivity(intent);
                                    }
                                }
                            });


                        } else {

                            Toast.makeText(SignUp.this, "Đăng kí thất bại",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}