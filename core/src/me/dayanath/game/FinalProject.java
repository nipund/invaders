package me.dayanath.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class FinalProject extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;
	int playerX, playerY, screenX, screenY;
	boolean holding;
	private boolean fire;
	private ArrayList<Missile> missiles = new ArrayList<Missile>();
	private ArrayList<Alien> aliens = new ArrayList<Alien>();
	private Texture missile_img;
	private Texture alien_img;
	private int frame;
	public int score;
	BitmapFont font, font2;
	private boolean gameOver;
	private float difficulty = 8;
	private GameOverCallback gameOverCallback;

	@Override
	public void create () {
		batch = new SpriteBatch();

		font = new BitmapFont(false);
		font2 = new BitmapFont(false);
		font.getData().setScale(-3, -3);
		font2.getData().setScale(-6, -6);

		img = new Texture("player.png");
		missile_img = new Texture("missile.png");
		alien_img = new Texture("enemy.png");
		Gdx.app.log("startup","ok");
		Gdx.input.setInputProcessor(this);
		playerX = Gdx.graphics.getWidth() / 2;
		playerY = Gdx.graphics.getHeight() - img.getHeight();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 4; j++) {
				Alien a = new Alien();
				a.x = 800 + 100 * i;
				a.y = 300 - 100 * j;
				a.sprite = new Sprite(alien_img);
				aliens.add(a);
			}
		}
	}

	@Override
	public void render () {
		frame++;
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(fire) {
			fire = false;
			Gdx.app.log("Fire", "Yes");
			Missile m = new Missile();
			m.x = playerX + 20;
			m.y = playerY - 20;
			m.sprite = new Sprite(missile_img);
			missiles.add(m);
			return;
		}

		if(holding) {
			if(screenX > Gdx.graphics.getHeight() / 2) {
				playerX += 15;
			} else {
				playerX -= 15;
			}
		}

		outerloop:
		for(int i = 0; i < missiles.size(); i++) {
			for(int j = 0; j < aliens.size(); j++) {
				if(missiles.get(i).sprite.getBoundingRectangle().overlaps(aliens.get(j).sprite.getBoundingRectangle())) {
					missiles.remove(i);
					aliens.remove(j);
					score += 20;
					break outerloop;
				}
			}
		}

		batch.begin();

		if(gameOver) {
			font2.draw(batch, "Game Over.", Gdx.graphics.getWidth()/2 + 200, Gdx.graphics.getHeight()/2  - 50);
			gameOverCallback.StartActivity();
			batch.end();
			return;
		}

		batch.draw(img, playerX, playerY);

		for(Missile missile : missiles) {
			missile.draw(batch);
			missile.y -= 10;
		}

		for(Alien alien : aliens) {
			if(alien.y > playerY - 40) {
				gameOver = true;
				break;
			}
			alien.draw(batch);
			if(alien.x > Gdx.graphics.getWidth()) {
				alien.x = 0;
			}
			if(frame % (int)(80/difficulty) == 0) {
				alien.y += 15;
			}
			alien.x += (2 * difficulty);
		}

		font.draw(batch, "Score: " + score, 250, 50);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		if (button != Input.Buttons.LEFT || pointer > 0) return false;
		if(screenX < 100 && screenY < 100) {
			fire = true;
			return true;
		}
		//Gdx.app.log("X", screenX+"");
		//Gdx.app.log("Y", screenY+"");
		this.screenX = screenX;
		this.screenY = screenY;
		holding = true;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button != Input.Buttons.LEFT || pointer > 0) return false;
		holding = false;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public void setGameOverCallback(GameOverCallback goc) {
		gameOverCallback = goc;
	}

	public interface GameOverCallback {
		public void StartActivity();
	}
}
