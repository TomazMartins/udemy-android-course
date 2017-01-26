package edu.udemy.android.phraseforday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btn_new_phrase;
    private TextView txv_phrase;

    String[] phrases = {
            "Olá",
            "Vai dar tudo certo!",
            "Não há nada que você não consigo superar",
            "Tenha fé e mantenha o foco!",
            "Força sempre!",
            "Omnia Amo Vincit!"
    };

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_new_phrase = (Button) findViewById( R.id.btn_new_phrase );
        txv_phrase = (TextView) findViewById( R.id.txv_phrase );

        btn_new_phrase.setOnClickListener( new View.OnClickListener() {
            Random random = new Random();
            int randomNumber;

            @Override
            public void onClick( View v ) {
                randomNumber = random.nextInt(6);
                txv_phrase.setText( phrases[ randomNumber ] );
            }
        } );
    }
}
