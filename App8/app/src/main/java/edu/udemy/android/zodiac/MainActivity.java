package edu.udemy.android.zodiac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView lsv_zodiac;

    private String[] listZodiacs = {
            "Aries", "Taurus", "Gemini", "Cancer",
            "Leo", "Virgo", "Libra", "Scorpio",
            "Sagittarius", "Capricorn", "Aquarius", "Pisces"
    };

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        lsv_zodiac = (ListView) findViewById( R.id.lsv_zodiac );

        ArrayAdapter<String> zodiacAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listZodiacs
        );

        lsv_zodiac.setAdapter( zodiacAdapter );

    }
}
