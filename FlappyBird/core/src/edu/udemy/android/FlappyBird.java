package edu.udemy.android;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
    private SpriteBatch batch;

    private Texture[] birds;
    private Texture background;

    private int movement = 0;
    private int maxWidth = 0;
    private int maxHeight = 0;

    private double variation = 0;
    private float fallSpeed = 0;
    private float positionY;

    @Override
    public void create() {
        batch = new SpriteBatch();

        birds = new Texture[ 3 ];
        birds[ 0 ] = new Texture("bird_1.png");
        birds[ 1 ] = new Texture("bird_2.png");
        birds[ 2 ] = new Texture("bird_3.png");

        background = new Texture( "background.png" );

        maxWidth = Gdx.graphics.getWidth();
        maxHeight = Gdx.graphics.getHeight();

        positionY = maxHeight/2;

    }

    @Override
    public void render() {
        variation += Gdx.graphics.getDeltaTime() * 10;
        fallSpeed++;

        if( variation > 2 ) {
            variation = 0;
        }

        if( Gdx.input.justTouched() ) {
            fallSpeed = -10;
        }

        if( positionY > 0  || fallSpeed < 0 ) {
            positionY -= fallSpeed;
        }

        batch.begin();

        batch.draw( background, 0, 0, maxWidth, maxHeight );
        batch.draw( birds[ (int) variation ], 30, positionY );

        batch.end();
    }
}
