package project.edu.example.delicoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;

import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.model.Users;

public class Checkout extends AppCompatActivity {
            TextView tvaddress,tvSum2,textView7,tvSum,textView15_25k;
            ImageView back;
            TextView txtNameOrder;
            Button btn_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        initUi();
        initListener();
        showAddress();
        handleSumPrice();
    }
    private void initUi()
    {

        tvSum2 = findViewById(R.id.checkout_sum2);
        tvSum=findViewById(R.id.checkout_sum);
        back=findViewById(R.id.checkout_back);
        textView7=findViewById(R.id.textView7);
        textView15_25k=findViewById(R.id.textView15);
        txtNameOrder=findViewById(R.id.txtNameOrder);
        btn_pay=findViewById(R.id.button3);

    }
    private void initListener()
    {
        handleDayTime();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Checkout.this,Home.class);
                startActivity(intent);
            }
        });
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                Toast toast =  Toast.makeText(Checkout.this,
                                                        "Payment Success",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 20, 30);
                                toast.show();
                                Intent intent = new Intent(Checkout.this,Home.class);
                                startActivity(intent);
            }
        });
    }
    private void handleDayTime()
    {
        TextClock textClock = findViewById(R.id.textclock);
        String formatdate = "E, d-M-yyyy k:m:sa";
        textClock.setFormat12Hour(formatdate);
        textClock.setFormat24Hour(formatdate);
    }
    private void handleSumPrice()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String strSum = extras.getString("key_price");
            String strName = extras.getString("key_name");
            txtNameOrder.setText(strName);
            tvSum.setText(String.valueOf(strSum));
           tvSum2.setText(String.valueOf(Integer.parseInt(strSum) + Integer.parseInt(textView15_25k.getText().toString())));
        }
    }
    private void showAddress()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Users").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //snapshot là đối tượng
                Users user = snapshot.getValue(Users.class);
                textView7.setText(user.address);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
