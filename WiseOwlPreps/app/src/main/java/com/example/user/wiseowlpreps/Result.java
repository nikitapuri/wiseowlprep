package com.example.user.wiseowlpreps; /**
 * Created by user on 29/03/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class Result extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //get rating bar object
        //RatingBar bar=(RatingBar)findViewById(R.id.ratingBar);
        //bar.setNumStars(5);
        //bar.setStepSize(0.5f);
        //get text view
        TextView t=(TextView)findViewById(R.id.Result);
        //get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        //display score
        //bar.setRating(score);
        switch (score)
        {
            case 1:
            case 2: t.setText("");
                break;
            case 3:
            case 4:t.setText("");
                break;
            case 5:t.setText("");
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }
}
