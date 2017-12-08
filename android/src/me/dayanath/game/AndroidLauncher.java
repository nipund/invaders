package me.dayanath.game;

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
		fp = new FinalProject();
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
