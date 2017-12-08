package me.dayanath.game;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends Activity {

    private TextView scoreText;
    private int score;
    private DatabaseHandler db;
    private Button button;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score = getIntent().getIntExtra("score", 0);
        db = new DatabaseHandler(this);

        username = (EditText) findViewById(R.id.name);

        scoreText = (TextView) findViewById(R.id.score);
        scoreText.setText("Score: " + score);

        button = (Button) findViewById(R.id.addToHS);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addScore(username.getText().toString(), score);
                Intent i = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}
