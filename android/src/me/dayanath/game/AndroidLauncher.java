package me.dayanath.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import me.dayanath.game.FinalProject;

public class AndroidLauncher extends AndroidApplication implements FinalProject.GameOverCallback{
	private FinalProject fp;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		int dif = getSharedPreferences("com.example.app", Context.MODE_PRIVATE).getInt("difficulty", 1);
		Log.d("Prefs-to-game", dif+"");
		fp = new FinalProject();
		fp.difficulty = dif;
		fp.setGameOverCallback(this);
		initialize(fp, config);
	}

	@Override
	public void StartActivity() {
		Intent i = new Intent(this, GameOverActivity.class);
		i.putExtra("score", fp.score);
		startActivity(i);
	}
}
