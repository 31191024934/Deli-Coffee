package project.edu.example.delicoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.w3c.dom.Text;

public class Profile_S extends AppCompatActivity {
            EditText edt_email,edt_name;
            TextView edt_signout ;
            ImageButton img_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_s);
        initUi();
        initListenerr();
        showUserInformation();
    }


    private void initUi() {
        edt_email= findViewById(R.id.profie_edt_email);
        edt_name= findViewById(R.id.profile_edt_name);
        edt_signout =findViewById(R.id.profile_sign_out);
        img_edit=findViewById(R.id.profile_imgbtn_edit);
    }
    private void initListenerr() {

        edt_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignOut();
            }
        });
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickImgEdit();
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
        Intent intent = new Intent(Profile_S.this,SingIn.class);
        startActivity(intent);
        finish();// đóng main lại
    }
    private void showUserInformation()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // kiem tra user ton tai hay k
        if(user == null)
        {
            return;
        }
        else
        {
            String email = user.getEmail();

//            String name = user.getDisplayName();
//            if(name == null) // kiem tra ten
//            {
//                edt_name.setText(View.GONE);//ẩn tên
//            }
//            else {
//                edt_name.setText(View.VISIBLE);
//                edt_name.setText(name);
//            }
            edt_email.setText(email);
        }

    }

}