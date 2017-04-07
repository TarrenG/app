package cs.assignment1cs412;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {



    ArrayList<String> qList;
    boolean active = false;
    String curAns = "";

    public void setAns(String ans) {
        curAns = ans;
    }

    public String getCurAns() {
        return curAns;
    }

    public ArrayList<String> initList () {
        ArrayList<String> qList = new ArrayList<String>();
        qList.add("What is the average par for an 18 hole golf course?72");
        qList.add("How many times has Tiger Woods won the Masters Tournament?4");
        qList.add("This is a third trivia question?ok");
        return qList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qList = initList();
    }

    public ArrayList<String> getList() {
        return qList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean bool) {
        active = bool;
    }
/*
    public void setTriviaQuestion(View v) {
        ArrayList<String> qList = new ArrayList<String>();
        qList.add("What is the average par for an 18 hole golf course?72");
        qList.add("How many times has Tiger Woods won the Masters Tournament?4");
        qList.add("This is a third trivia question?ok");
        while (!qList.isEmpty()) {
            Random r = new Random();
            int idx = r.nextInt(qList.size());
            TextView textViewToChange = (TextView) findViewById(R.id.question);
            String newQ = qList.get(idx);
            newQ = newQ.split("\\?")[0];
            String ans = newQ.split("\\?")[1];
            qList.remove(idx);
            Log.w("MainActivity", "q is "+newQ);
            textViewToChange.setText(newQ);
        }
        TextView textViewToChange = (TextView) findViewById(R.id.question);
        textViewToChange.setText("You've done all the questions!");
    } */

    public void onClick (View v) {
        ArrayList<String> list = getList();
        boolean started = isActive();
        Random r = new Random();
        TextView pointsText = (TextView) findViewById(R.id.userPoints);
        Log.w("debug", "pointsText is "+pointsText);
        int points = Integer.parseInt(pointsText.getText().toString());

        EditText editText;
        if (!started) {
            setActive(true);
        }

        if (!started) {
            int idx = r.nextInt(list.size());
           // Log.w("debug", "list size is "+list.size()+", idx is "+idx);
            TextView textViewToChange = (TextView) findViewById(R.id.question);
            String newQ = list.get(idx);
            String question = newQ.split("\\?")[0];
            //Log.w("debug", "newQ is  "+newQ);
            String ans = newQ.split("\\?")[1];
            setAns(ans);
           // Log.w("debug", "ans is  "+ans);
            list.remove(idx);
           // Log.w("debug", "list size is "+list.size()+" after removal");
           // Log.w("debug", "q is "+question);
            textViewToChange.setText(question+"?");
        }
        else if (started && !list.isEmpty()) {
           // Log.w("debug", "we have started and list isnt empty");
            editText = (EditText) findViewById(R.id.userGuess);
            String guess = editText.getText().toString();
           // Log.w("debug", "USER has guessed "+guess+", correct ans is "+getCurAns());

            if(guess.toLowerCase().equals(getCurAns().toLowerCase())) {
             //   Log.w("debug", "guessed correctly");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP | Gravity.LEFT,0,0);
                toast.makeText(MainActivity.this, "Correct!", toast.LENGTH_SHORT).show();
                // Ask new Question
                int idx = r.nextInt(list.size());
             //   Log.w("debug", "list size is "+list.size()+", idx is "+idx);
                TextView textViewToChange = (TextView) findViewById(R.id.question);
                String newQ = list.get(idx);
                String question = newQ.split("\\?")[0];
             //   Log.w("debug", "newQ is  "+newQ);
                String ans = newQ.split("\\?")[1];
                setAns(ans);
              //  Log.w("debug", "ans is  "+ans);
                list.remove(idx);
              //  Log.w("debug", "list size is "+list.size()+" after removal");
             //   Log.w("debug", "q is "+question);
                textViewToChange.setText(question+"?");
                points++;
                Log.w("debug", "incremented pts to "+points);
                pointsText.setText(Integer.toString(points));
            }
            else {
               // Log.w("debug", "guessed incorrectly");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP | Gravity.LEFT,0,0);
                toast.makeText(MainActivity.this, "Wrong!", toast.LENGTH_SHORT).show();
                points--;
                Log.w("debug", "decremented pts to "+points);
                pointsText.setText(Integer.toString(points));
            }
        }
        else if (list.isEmpty()) {
            if (getCurAns().equals("!!#@#####$@(&$^@user^^done((")) {
                TextView textViewToChange = (TextView) findViewById(R.id.question);
                textViewToChange.setText("You've done all the questions!");
            }
            else {
                editText = (EditText) findViewById(R.id.userGuess);
                Log.w("debug", "points is "+pointsText.toString());
                String guess = editText.getText().toString();
                Log.w("debug", "USER has guessed " + guess + ", correct ans is " + getCurAns());

                if (guess.toLowerCase().equals(getCurAns().toLowerCase())) {
                   // Log.w("debug", "guessed correctly");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                    toast.makeText(MainActivity.this, "Correct!", toast.LENGTH_SHORT).show();
                    setAns("!!#@#####$@(&$^@user^^done((");
                    points++;
                    Log.w("debug", "incremented pts to "+points);
                    pointsText.setText(Integer.toString(points));
                } else {
                   // Log.w("debug", "guessed incorrectly");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                    toast.makeText(MainActivity.this, "Wrong!", toast.LENGTH_SHORT).show();
                    points--;
                    Log.w("debug", "decremented pts to "+points);
                    pointsText.setText(Integer.toString(points));
                }
                TextView questionText = (TextView) findViewById(R.id.question);
                questionText.setText("You've done all the questions!");
            }
        }
        //Log.w("debug", "all done?????????");
    }
}
