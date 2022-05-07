package project.edu.example.delicoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.adapter.CartsAdapter;
import project.edu.example.delicoffee.model.Product;
import project.edu.example.delicoffee.model.Users;

public class Product_Detail extends AppCompatActivity {
    private TextView txtName,txtPrice,txtDescription,txtQuantity;
    private Button btnMinus,btnPlus,btnBack,btnAddCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initUi();
        getDetailProduct();
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MinusQuantityProduct();
                return;
            }
        });
        PlusQuantityProduct();
        getBackHomeActivity();
        addToCarts();
    }

    private void initUi(){
        txtName=findViewById(R.id.txtName);
        txtPrice=findViewById(R.id.txtPrice);
        txtDescription=findViewById(R.id.txtDescription);
        btnMinus=findViewById(R.id.btnMinus);
        btnPlus=findViewById(R.id.btnPlus);
        btnBack=findViewById(R.id.btnBack);
        txtQuantity=findViewById(R.id.txtQuantity);
        btnAddCart= findViewById(R.id.detail_add_to_cart);

    }
    private void getDetailProduct()
    {
        Bundle bundle=getIntent().getExtras();
        Product product= (Product) bundle.get("key_product");
        txtPrice.setText(String.valueOf(Integer.valueOf(product.getPrice())*Integer.valueOf(txtQuantity.getText().toString())));
        txtName.setText(product.getName());
//        txtPrice.setText(String.valueOf(Integer.valueOf(product.getPrice())*Integer.valueOf(txtQuantity.getText().toString())));
        txtDescription.setText(product.getDescription());
    }
    private void getBackHomeActivity()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Product_Detail.this,Home.class);
                startActivity(intent);
            }
        });

    }
    private void MinusQuantityProduct() {
        if (Integer.parseInt(txtQuantity.getText().toString()) == 1) {
            return;
        }
        txtQuantity.setText(String.valueOf(Integer.parseInt(txtQuantity.getText().toString()) - 1));
        getDetailProduct();
    }
    private void PlusQuantityProduct()
    {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtQuantity.setText(String.valueOf(Integer.parseInt(txtQuantity.getText().toString())+1));
                getDetailProduct();
                return;
            }
        });
    }
    private void setCartFromRealTimeDatabase()
    {

    }

    private void addToCarts()
    {
        btnAddCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_Detail.this,Checkout.class);
                Bundle bundle = new Bundle();
                bundle.putString("key_price",txtPrice.getText().toString());
                bundle.putString("key_name",txtName.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}