package net.kathir.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.kathir.firebasecrud.model.ListModel;

public class MainActivity extends AppCompatActivity {

    EditText title,desc;
    Button notesSave;
    String titlesend,descsend;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        notesSave = findViewById(R.id.addnotes);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        notesSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotes();
            }
        });
    }

    private void addNotes() {
        titlesend=title.getText().toString();
        descsend=desc.getText().toString();
        if(TextUtils.isEmpty(titlesend) || TextUtils.isEmpty(descsend)){
            return;
        }
        insertNotes(titlesend,descsend);
    }

    private void insertNotes(String titlesend, String descsend) {
        String id= mDatabase.push().getKey();
        ListModel listModel = new ListModel(id,titlesend,descsend);
        mDatabase.child("Notes").child(id).setValue(listModel).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Notes Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                    }
                });

    }


}

