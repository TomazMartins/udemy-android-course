package edu.udemy.android;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
    final static private float SPACE_BETWEEN_PIPES = 200; // In Pixels

    private SpriteBatch batch;

    private Texture[] birds;
    private Texture background;


    private Texture pipeTop;
    private Texture pipeBottom;

    private int movement = 0;

    private double variation = 0;
    private float fallSpeed = 0;

    // This measures are in Pixels
    private int maxWidth = 0;
    private int maxHeight = 0;
    private float positionY;
    private float positionPipeX = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();

        birds = new Texture[ 3 ];
        birds[ 0 ] = new Texture( "bird_1.png" );
        birds[ 1 ] = new Texture( "bird_2.png" );
        birds[ 2 ] = new Texture( "bird_3.png" );

        background = new Texture( "background.png" );

        pipeBottom = new Texture( "pipe_bottom_up.png" );
        pipeTop = new Texture( "pipe_top_down.png" );

        maxWidth = Gdx.graphics.getWidth();
        maxHeight = Gdx.graphics.getHeight();

        positionY = maxHeight / 2;

        positionPipeX = maxWidth - 100;

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

        if( positionY > 0 || fallSpeed < 0 ) {
            positionY -= fallSpeed;
        }

        batch.begin();

        batch.draw( background, 0, 0, maxWidth, maxHeight );
        batch.draw( pipeTop, positionPipeX, (maxHeight / 2 + SPACE_BETWEEN_PIPES / 2) );

        batch.draw( pipeBottom, positionPipeX,
                    (maxHeight / 2 - pipeBottom.getHeight() - SPACE_BETWEEN_PIPES / 2)
                  );

        batch.draw( birds[ (int) variation ], 30, positionY );

        batch.end();
    }
}
