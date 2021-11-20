package com.example.morningdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.morningdbdemo.data.DatabaseHandler;
import com.example.morningdbdemo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ViewAllProducts extends AppCompatActivity {

    ListView viewAllProducts;
    private ArrayList<String> productArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_products);

        productArrayList = new ArrayList<>();
        viewAllProducts = findViewById(R.id.productList);

        DatabaseHandler db = new DatabaseHandler(ViewAllProducts.this);

        db.getAllProducts();

        List<Product> productList =  db.getAllProducts();

        for(Product product: productList) {
            Log.d("ViewActivity", "On Create: " + product.getName() + ", " + product.getId());
            productArrayList.add(product.getName());
        }

        //create array adapter
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                productArrayList
        );

        //add to our listview
        viewAllProducts.setAdapter(arrayAdapter);

        //Attach eventlistener to listview

        viewAllProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Price: " + productList.get(position).getPrice(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}