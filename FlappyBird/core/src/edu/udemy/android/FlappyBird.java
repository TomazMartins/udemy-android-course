package edu.udemy.android;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
    private SpriteBatch batch;

    private Texture bird;
    private Texture background;

    private int movement = 0;
    private int maxWidth = 0;
    private int maxHeight = 0;

    @Override
    public void create() {
        maxWidth = Gdx.graphics.getWidth();
        maxHeight = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        bird = new Texture( "bird_1.png" );
        background = new Texture( "background.png" );
    }

    @Override
    public void render() {
        movement++;

        batch.begin();

        batch.draw( background, 0, 0, maxWidth, maxHeight );
        batch.draw( bird, movement, 400 );

        batch.end();
    }
}
