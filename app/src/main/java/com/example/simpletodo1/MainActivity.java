package com.example.simpletodo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> items;
    Button bAdd;
    EditText item1;
    RecyclerView rec;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAdd = findViewById(R.id.bAdd);
        item1 = findViewById((R.id.item1));
        rec = findViewById(R.id.rec);

        items = new ArrayList<>();
        items.add("");
        items.add("");
        items.add("");



        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                items.remove(position);
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Items was removed", Toast.LENGTH_SHORT).show();
            }};
         itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        rec.setAdapter(itemsAdapter);
        rec.setLayoutManager(new LinearLayoutManager(this));

        bAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String todoItem = item1.getText().toString();
                items.add(todoItem);
                itemsAdapter.notifyItemInserted(items.size() -1);
                item1.setText("");
                Toast.makeText(getBaseContext(),"Items was added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}