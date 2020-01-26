package jiang.henry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActorDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);


        // Unpacking the film passed from Intent
        Intent intent = getIntent();
        Bundle data = getIntent().getExtras();
        Person a = (Person) data.getParcelable("Actor");

        TextView name = findViewById(R.id.details);
        name.setText(a.printDescription());
    }
}
