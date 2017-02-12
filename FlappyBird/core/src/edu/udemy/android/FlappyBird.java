package edu.udemy.android;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    final static private int NO_STARTED = 0;
    final static private int STARTED = 1;
    final static private int END = 2;

    final static private float SPACE_BETWEEN_PIPES = 300; // In Pixels
    final static private float POSITION_BIRD_X = 120;


    private SpriteBatch batch;

    private Texture[] birds;
    private Texture background;
    private Texture pipeTop;
    private Texture pipeBottom;

    private Circle formBird;
    private Rectangle formPipeTop;
    private Rectangle formPipeBottom;
    private ShapeRenderer shape;

    private BitmapFont font;
    private Random random;

    private int gameState = NO_STARTED;
    private boolean is_scored = false;

    private int score = 0 ;

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

        font = new BitmapFont();
        font.setColor( Color.WHITE );
        font.getData().setScale( 4 );

        formBird = new Circle();
        formPipeTop = new Rectangle();
        formPipeBottom = new Rectangle();

        shape = new ShapeRenderer();

        createTextures();

        maxWidth = Gdx.graphics.getWidth();
        maxHeight = Gdx.graphics.getHeight();

        positionBirdY = maxHeight / 2;

        positionPipeX = maxWidth - 100;

    }

    @Override
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();

        flapWings();

        verifyGameState();

        drawObjects();

        formBird.set( (120 + birds[ 0 ].getWidth()/2),
                (positionBirdY + birds[ 0 ].getHeight()/2), (birds[ 0 ].getHeight()/2)
        );

        formPipeBottom.set( positionPipeX,
                (maxHeight / 2 - pipeBottom.getHeight() - SPACE_BETWEEN_PIPES / 2 + heightRandom),
                pipeBottom.getWidth(), pipeBottom.getHeight()
        );

        formPipeTop.set( positionPipeX,
                (maxHeight / 2 + SPACE_BETWEEN_PIPES / 2 + heightRandom),
                pipeTop.getWidth(), pipeTop.getHeight()
        );

        shape.begin( ShapeRenderer.ShapeType.Filled );
        shape.circle( formBird.x, formBird.y, formBird.radius );
        shape.rect( formPipeBottom.x, formPipeBottom.y, formPipeBottom.width, formPipeBottom.height );
        shape.rect( formPipeTop.x, formPipeTop.y, formPipeTop.width, formPipeTop.height );
        shape.end();
    }

    private void verifyGameState() {
        if( gameState == NO_STARTED ) {
            if( Gdx.input.justTouched() ) {
                gameState = STARTED;
            }
        } else {
            fallSpeed++;

            getPositionPipe();

            flyBird();

            fallBird();

            movePipes();

            incrementScore();
        }
    }

    private void incrementScore() {
        if( positionPipeX < POSITION_BIRD_X ) {
            if( !is_scored ) {
                score++;
                is_scored = true;
            }
        }
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

        batch.draw( birds[ (int) variation ], POSITION_BIRD_X, positionBirdY );

        font.draw( batch, String.valueOf( score ), (maxWidth/2), (maxHeight-50) );

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

            is_scored = false;
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
