package com.example.cloud_chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static int Max_Message_Length = 100;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("messages");

    EditText mEditTextMessage;
    Button mSendButton;
    RecyclerView mMessagesRecycler;

    ArrayList<String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendButton = findViewById(R.id.send_message);
        mEditTextMessage = findViewById(R.id.message_input);
        mMessagesRecycler = findViewById(R.id.message_recycler);

        mMessagesRecycler.setLayoutManager(new LinearLayoutManager(this));

        DataAdapter dataAdapter = new DataAdapter(this,messages);

        mMessagesRecycler.setAdapter(dataAdapter);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = mEditTextMessage.getText().toString();
                if (msg.equals("")){
                    android.widget.Toast.makeText(getApplicationContext(), "Введите сообщение", Toast.LENGTH_SHORT).show();
                    return; 
                }
                if (msg.length() > Max_Message_Length) {
                    android.widget.Toast.makeText(getApplicationContext(), "Слишком длинное сообщение", Toast.LENGTH_SHORT).show();
                    return;
                }
                myRef.push().setValue(msg);
                mEditTextMessage.setText("");
            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot dataSnapshot, @Nullable @org.jetbrains.annotations.Nullable String s) {
                String msg = dataSnapshot.getValue(String.class);
                messages.add(msg);
                dataAdapter.notifyDataSetChanged();
                mMessagesRecycler.smoothScrollToPosition(messages.size());
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot dataSnapshot, @Nullable @org.jetbrains.annotations.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot dataSnapshot, @Nullable @org.jetbrains.annotations.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }
        }); {

            }
        }
    }
