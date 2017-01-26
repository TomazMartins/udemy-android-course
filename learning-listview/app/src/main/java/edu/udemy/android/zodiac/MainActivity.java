package edu.udemy.android.zodiac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lsv_zodiac;

    private String[] listZodiacs = {
            "Aries", "Taurus", "Gemini", "Cancer",
            "Leo", "Virgo", "Libra", "Scorpio",
            "Sagittarius", "Capricorn", "Aquarius", "Pisces"
    };

    private String[] listZodiacDescriptions = {
            "Adventurous, Active and Outgoing, Naive, Child at heart, Athletic, Dynamic and Aggressive",
            "Cool, detached and obstinate, Reserved and withdrawn, Love of Luxury, Sensual, devoted and kind",
            "Mercury Ruled and multi-talented, Communicators of the Zodiac, Adventurous and full of fun",
            "Compassionate and Family Orientated, Emotional and Sensitive, Romantic and Mysterious",
            "Charismatic and Stylish, Loyal and Devoted, Soul evolution of Leo, Proud and Born to Lead",
            "Ethical with High Standards, Self-sacrificing and considerate, Creative and Sensitive, Reliable and Understanding",
            "Harmony and Co-operation, A Need for Relating, Peace Loving and Refined, Unbiased and Non-Judgemental",
            "Powerful, Dramatic and Wise, Secretive, Passionate and Intense, Faithful and Unforgiving, Soul Evolution",
            "Optimistic and Confident, Free-spirited and Spontaneous, Enthusiastic and Idealistic, Seekers of Wisdom",
            "The Mountain Goat and Garden Goat, Ambitious, Cautious and Conservative, Responsible and Reliable",
            "Trendsetters of the Future, Intellectual and Innovative, Humanitarian and Objective, Socially aware",
            "Alluring and Talented, Compassionate and Understanding, Sensitive and Receptive, Imaginative and other worldly"
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

        lsv_zodiac.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                int zodiacSign = position;

                Toast.makeText( getApplicationContext(), listZodiacDescriptions[ zodiacSign ],
                        Toast.LENGTH_LONG ).show();
            }
        } );

    }
}
