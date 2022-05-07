package project.edu.example.delicoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.xml.transform.Result;

import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.model.Users;

public class Profile_S extends AppCompatActivity {
         private    EditText edt_email,edt_name,edt_phone,edt_address;
       private      TextView edt_signout ;
     private ImageButton img_editemai,img_editphone,img_editname,img_editaddress;
         private    Button btn_save;
         private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_s);
        initUi();
        initListenerr();
        showUserInformationemail();
        showUserInformation();
    }


    private void initUi() {
        edt_email= findViewById(R.id.profie_edt_email);
        edt_name= findViewById(R.id.profile_edt_name);
        edt_signout =findViewById(R.id.profile_sign_out);
        img_editemai=findViewById(R.id.profile_img_editemail);
        img_editphone=findViewById(R.id.profile_img_editphone);
        img_editname=findViewById(R.id.profile_img_editname);
        img_editaddress=findViewById(R.id.profile_img_editaddress);
        edt_phone=findViewById(R.id.profile_edt_phone);
        btn_save=findViewById(R.id.profile_btn_save);
        img_back=findViewById(R.id.profile_img_back);
        edt_address= findViewById(R.id.profile_edt_address);
    }
    private void initListenerr() {

        edt_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignOut();
            }
        });
        img_editemai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickImgEdit();
            }

        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEditInfomation();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile_S.this,Home.class);
                startActivity(intent);
            }
        });


    }



    private void onClickImgEdit()
        {
            String stremail= edt_email.getText().toString().trim();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(stremail)

                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Profile_S.this,"Update successful",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    private void onClickSignOut()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Profile_S.this, SingIn.class);
        startActivity(intent);
        finish();// đóng main lại
    }
    private void showUserInformationemail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // kiem tra user ton tai hay k
        if(user == null)
        {
            return;
        }
        else
        {
            String email = user.getEmail();

            String name = user.getDisplayName();
//            if(name == null) // kiem tra ten
//            {
//                edt_name.setText(View.GONE);//ẩn tên
//            }
//            else {
//                edt_name.setText(View.VISIBLE);
//                edt_name.setText(name);
//            }

        }

    }
    private void showUserInformation(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
     DatabaseReference databaseReference=firebaseDatabase.getReference("Users").child(uid);
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) { //snapshot là đối tượng
            Users user = snapshot.getValue(Users.class);
            edt_name.setText(user.username);
            edt_email.setText(user.email);
            edt_phone.setText(user.sdt);
            edt_address.setText(user.address);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


    }
    private void onClickEditInfomation() {
        String strphone = edt_phone.getText().toString().trim();
        String stremail = edt_email.getText().toString().trim();
        String strname= edt_name.getText().toString().trim();
        String straddress= edt_address.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Users").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //snapshot là đối tượng
                Users user = snapshot.getValue(Users.class);
                edt_name.setText(user.username);
                edt_email.setText(user.email);
                edt_phone.setText(user.sdt);
                edt_address.setText(user.address);
                Users updateUser=new Users();
                updateUser.setEmail(stremail);
                updateUser.setUsername(strname);
                updateUser.setSdt(strphone);
                updateUser.setAddress(straddress);
                databaseReference.setValue(updateUser);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}