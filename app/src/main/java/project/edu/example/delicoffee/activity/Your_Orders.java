package project.edu.example.delicoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.adapter.CartsAdapter;
import project.edu.example.delicoffee.adapter.ProductAdapter;
import project.edu.example.delicoffee.model.Product;

public class Your_Orders extends AppCompatActivity {
  private   List<Product> products;
   private CartsAdapter cartsAdapter;
   private RecyclerView rvcHomeProduct;
   LinearLayout L_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);
        rvcHomeProduct=findViewById(R.id.recylerview_order);
        L_back=findViewById(R.id.order_back);
        L_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent (Your_Orders.this,Home.class);
                startActivity(intent);
            }
        });
        getListProduct();
        getListFromDatabase();
    }
    public void getListProduct()
    {
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager=new LinearLayoutManager(Your_Orders.this,LinearLayoutManager.VERTICAL,false);
        rvcHomeProduct.setLayoutManager(linearLayoutManager);
        products=new ArrayList<>();
        cartsAdapter=new CartsAdapter(this,products);
        rvcHomeProduct.setAdapter(cartsAdapter);
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
                    if(product != null)
                    {
                        products.add(product);
                        cartsAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}