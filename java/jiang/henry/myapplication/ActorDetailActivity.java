package jiang.henry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * An activity specifically for actors.
 * Displays their description
 */
public class ActorDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);

        // Unpacking the film passed from Intent
        Intent intent = getIntent();
        Bundle data = getIntent().getExtras();
        Actor a = (Actor) data.getParcelable("Actor");

        TextView name = findViewById(R.id.details);
        name.setText(a.printDescription());
    }
}
