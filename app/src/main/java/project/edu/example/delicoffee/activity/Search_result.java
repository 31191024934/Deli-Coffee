package project.edu.example.delicoffee.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.adapter.ProductAdapter;
import project.edu.example.delicoffee.model.Product;

public class Search_result extends AppCompatActivity {
    ImageView img_search ;
    LinearLayout Search_back;
    private List<Product> listProducts;
    private ProductAdapter productAdapter;
    RecyclerView rvcHomeProduct;
    EditText edt_search;
    TextView tv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initUi();
        initListener();
        getListProduct();



    }
    private void initUi()
    {
        img_search=findViewById(R.id.search_img_search_result);
        rvcHomeProduct=findViewById(R.id.rvcHomeProduct_search);
        edt_search= findViewById(R.id.searchResult_edt_search);
        Search_back=findViewById(R.id.Search_back);
        tv_back=findViewById(R.id.search_back);
    }
    private void initListener()
    {
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Search_result.this,Home.class);
                startActivity(intent);
            }
        });
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //       Toast.makeText(Search_result.this,"kích oke",Toast.LENGTH_SHORT).show();
                getListFromDatabase();

            }
        });
        Search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search_result.this,Home.class);
            }
        });
    }

    public void getListProduct()
    {
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager=new LinearLayoutManager(Search_result.this,LinearLayoutManager.VERTICAL,false);
        rvcHomeProduct.setLayoutManager(linearLayoutManager);
        listProducts=new ArrayList<>();
        productAdapter=new ProductAdapter(this,listProducts);
        rvcHomeProduct.setAdapter(productAdapter);
    }
    public void getListFromDatabase()
    {

        String strsearch= edt_search.getText().toString().toLowerCase().trim();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Product");

    //    Query query = databaseReference.limitToFirst(2);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Product product=dataSnapshot.getValue(Product.class);

                        if(product != null)
                        {
                            if(product.getName().toLowerCase().contains(strsearch) == true)
                            {
                                listProducts.add(product);


                            }

                            else
                            {

                                Toast toast =  Toast.makeText(Search_result.this,"No Result Found !!",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 20, 30);
                                toast.show();

                            }
                            productAdapter.notifyDataSetChanged();
                        }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}