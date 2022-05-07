package project.edu.example.delicoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.adapter.ProductAdapter;
import project.edu.example.delicoffee.model.Cart;
import project.edu.example.delicoffee.model.Product;

public class Home extends AppCompatActivity {
List<Product> products;
ProductAdapter productAdapter;
RecyclerView rvcHomeProduct;
ImageView imgSearch,imgCarts;
public static ArrayList<Cart> listcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUi();
        initListenrt();
        ImageView imgProfile=findViewById(R.id.home_profile);
        getListProduct();
        getListFromDatabase();
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLoginInProfile();

            }
        });


    }
private void initUi()
{
    rvcHomeProduct=findViewById(R.id.rvcHomeProduct);
    imgSearch=findViewById(R.id.home_search);
    imgCarts=findViewById(R.id.home_carts);

    if (listcart != null)
    {

    }
    else {
        listcart= new ArrayList<>();
    }

}
private void initListenrt()
{
    imgSearch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Home.this,Search_result.class);
            startActivity(intent);
        }
    });
    imgCarts.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Home.this,Your_Orders.class);
            startActivity(intent);
        }
    });

}

    public void getListProduct()
    {
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager=new LinearLayoutManager(Home.this,LinearLayoutManager.VERTICAL,false);
        rvcHomeProduct.setLayoutManager(linearLayoutManager);
        products=new ArrayList<>();
        productAdapter=new ProductAdapter(this,products);
        rvcHomeProduct.setAdapter(productAdapter);
    }
    public void getListFromDatabase()
    {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Product");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Product product=dataSnapshot.getValue(Product.class);
                    products.add(product);
                    productAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
   private void checkLoginInProfile()
    {
        // kiemr tra login hay chưa
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            // chưa login
            Intent intent = new Intent(Home.this, SingIn.class);
           startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(Home.this, Profile_S.class);
           startActivity(intent);
        }
            finish();
    }
}
