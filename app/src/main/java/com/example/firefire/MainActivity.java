package com.example.firefire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button logout;
    private EditText edit;
    private Button add;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=findViewById(R.id.logout);
        edit=findViewById(R.id.edit);
        add=findViewById(R.id.add);
        listView=findViewById(R.id.listView);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,StartActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_name=edit.getText().toString();
                if(txt_name.isEmpty()){
                    Toast.makeText(MainActivity.this,"No name entered",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://firefire-dc8be-default-rtdb.asia-southeast1.firebasedatabase.app/");
                    DatabaseReference myRef = database.getReference("Languages");
                    DatabaseReference Child1=myRef.child("Name");
                    Child1.setValue(txt_name);

                }
            }
        });
        ArrayList<String> list=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.list_item,list);
        listView.setAdapter(adapter);
        DatabaseReference reference=FirebaseDatabase.getInstance("https://firefire-dc8be-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Information");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Information info=snapshot1.getValue(Information.class);
                    String txt=info.getName()+":"+info.getEmail();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}