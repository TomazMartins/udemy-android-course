package edu.udemy.android;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    final static private float SPACE_BETWEEN_PIPES = 300; // In Pixels

    private SpriteBatch batch;

    private Texture[] birds;
    private Texture background;


    private Texture pipeTop;
    private Texture pipeBottom;

    private Random random;

    private double variation = 0;
    private float fallSpeed = 0;
    private float deltaTime;

    private int maxWidth = 0;       // In Pixels
    private int maxHeight = 0;      // In Pixels
    private float positionBirdY;    // In Pixels
    private float positionPipeX;    // In Pixels
    private float heightRandom;     // In Pixels

    @Override
    public void create() {
        batch = new SpriteBatch();
        random = new Random();

        createTextures();

        maxWidth = Gdx.graphics.getWidth();
        maxHeight = Gdx.graphics.getHeight();

        positionBirdY = maxHeight / 2;

        positionPipeX = maxWidth - 100;

    }

    @Override
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        fallSpeed++;

        flapWings();
        getPositionPipe();

        flyBird();

        fallBird();

        movePipes();

        drawObjects();
    }

    private void drawObjects() {
        batch.begin();

        batch.draw( background, 0, 0, maxWidth, maxHeight );

        batch.draw( pipeTop, positionPipeX,
                (maxHeight / 2 + SPACE_BETWEEN_PIPES / 2 + heightRandom)
        );

        batch.draw( pipeBottom, positionPipeX,
                (maxHeight / 2 - pipeBottom.getHeight() - SPACE_BETWEEN_PIPES / 2 + heightRandom)
        );

        batch.draw( birds[ (int) variation ], 120, positionBirdY );

        batch.end();
    }

    private void fallBird() {
        if( positionBirdY > 0 || fallSpeed < 0 ) {
            positionBirdY -= fallSpeed;
        }
    }

    private void movePipes() {
        if( positionPipeX < -(pipeTop.getWidth()) ) {
            positionPipeX = maxWidth;

            heightRandom = random.nextInt( 400 ) - 200;
        }
    }

    private void flyBird() {
        if( Gdx.input.justTouched() ) {
            fallSpeed = -15;
        }
    }

    private  void flapWings() {
        variation += deltaTime * 10;

        if( variation > 2 ) {
            variation = 0;
        }
    }

    private void getPositionPipe() {
        positionPipeX -= deltaTime * 200;
    }

    private void createTextures() {
        birds = new Texture[ 3 ];
        birds[ 0 ] = new Texture( "bird_1.png" );
        birds[ 1 ] = new Texture( "bird_2.png" );
        birds[ 2 ] = new Texture( "bird_3.png" );

        background = new Texture( "background.png" );

        pipeBottom = new Texture( "pipe_bottom_up.png" );
        pipeTop = new Texture( "pipe_top_down.png" );
    }
}
