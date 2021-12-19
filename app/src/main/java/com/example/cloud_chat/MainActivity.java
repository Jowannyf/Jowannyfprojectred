package com.example.cloud_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static void int Max_Message_Length = 100;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("messages");
    Button button;
    EditText mEditTextMessage;
    Button mSendButton;

    ArrayList<String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendButton = findViewById(R.id.send_message);
        mEditTextMessage = findViewById(R.id.message_input);

        mSendButton.setOnCickListener(new View.OnClickListener() {
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
        myRef.addChildEventlistener(new ChildEventListener(){
            @Override
            public void onClickAdded(DataSnapshot, String s) {
                String msg = dataSnapshot.getValue(String.class);
                messages.add(msg);
            }
        });
    }
}