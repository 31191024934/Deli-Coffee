package project.edu.example.delicoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Enter_Otp extends AppCompatActivity {
    EditText edt_code;
    Button btn_code;
    TextView btn_resend_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        initUi();
        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strcode= edt_code.getText().toString().trim();
                onClickSendCode(strcode);
            }
        });

    }

    private void onClickSendCode(String strcode) {
    }


    private void initUi() {
        edt_code=findViewById(R.id.send_edt_code);
        btn_code=findViewById(R.id.send_btn_code);
        btn_resend_otp=findViewById(R.id.send_tv_otp);
    }

}
