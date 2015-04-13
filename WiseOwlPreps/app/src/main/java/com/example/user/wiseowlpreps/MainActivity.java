package com.example.user.wiseowlpreps; /**
 * Created by user on 29/03/2015.
 */
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    List<Question> quesList;
    int score=0;
    int qid=0;
    Question currentQ;
    TextView txtQuestion;
    TextView resultView;
    TextView correctAnswerView;
    RadioButton rda, rdb, rdc;
    Button butNext;
    Button subButton;
    Button finishButton;
    private Timer t;
    private int TimeCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper db = new DbHelper(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.QuestionTextView);
        resultView = (TextView) findViewById(R.id.Result);
        correctAnswerView = (TextView) findViewById(R.id.CorrectAnswer);
        rda = (RadioButton) findViewById(R.id.button);
        rdb = (RadioButton) findViewById(R.id.button2);
        rdc = (RadioButton) findViewById(R.id.button3);
        butNext = (Button) findViewById(R.id.questionButton);
        subButton = (Button) findViewById(R.id.button4);

        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                runOnUiThread(new Runnable() {
                    public void run() {
                        //tvTimer.setText(String.valueOf(TimeCounter)); // you can set it to a textView to show it to the user to see the time passing while he is writing.
                        TimeCounter++;
                    }
                });

            }
        }, 1000, 1000);

        setQuestionView();
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                //Not needed for test mode
                //resultView.setText("You chose: " + answer.getText() + " " + "The correct answer is: " + currentQ.getANSWER());
                if (qid > 4)
                {
                    TextView correctQuestionView = (TextView) findViewById(R.id.Result);
                    correctQuestionView.setText("");
                }
                if (currentQ.getANSWER().equals(answer.getText())) {
                    score++;
                    //Not needed for test mode
                    //correctAnswerView.setText("Your score: " + score);
                }

            }
        });


        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (qid < 10) {
                    currentQ = quesList.get(qid);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(MainActivity.this, Result.class);
                    Bundle b = new Bundle();
                    b.putInt("Score: ", score);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }

    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;

        if(qid == 10)
        {
            TextView finish = (TextView) findViewById(R.id.questionButton);
            finish.setText("Finish");
        }

        //Manipulates layout at the end of the test
        if(qid > 9 )
        {
            correctAnswerView.setText("You scored " + score + " out of 10");
            TextView questionView = (TextView) findViewById(R.id.QuestionTextView);
            questionView.setText("");

            TextView results = (TextView) findViewById(R.id.Result);
            results.setText("The time taken is " + String.valueOf((TimeCounter)/10) + Toast.LENGTH_LONG + " Seconds");

            View rmRadioButton = findViewById(R.id.button);
            View rmRadioButton2 = findViewById(R.id.button2);
            View rmRadioButton3 = findViewById(R.id.button3);
            View rmnextQButton = findViewById(R.id.questionButton);
            View rmSubButton = findViewById(R.id.button4);
            rmRadioButton.setVisibility(View.GONE);
            rmRadioButton2.setVisibility(View.GONE);
            rmRadioButton3.setVisibility(View.GONE);
            rmnextQButton.setVisibility(View.GONE);
            rmSubButton.setVisibility(View.GONE);

        }

    }
}
