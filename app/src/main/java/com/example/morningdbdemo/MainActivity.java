package com.example.morningdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.morningdbdemo.data.DatabaseHandler;
import com.example.morningdbdemo.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        //Product product = new Product();

        //product.setName("Earphone");
        //product.setPrice((long)300.15);
        //product.setQuantity(15);

        //db.addProduct(product);

        List<Product> productList = db.getAllProducts();

        for(Product product: productList) {
            Log.d("MainActivity", "On Create: " + product.getName());
        }

    }
}