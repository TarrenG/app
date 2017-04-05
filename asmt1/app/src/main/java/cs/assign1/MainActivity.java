package cs.assign1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.net.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private EditText bname;

    public void buttonOnClick(View v){
        Button b = (Button) v;
        bname = (EditText) findViewById(R.id.editText);
        System.out.println("IN BUTTON CLICK _____________----------------------");
        try {
            URL boxrecURL = new URL("http://boxrec.com/media/index.php/" + bname);

            String payload = "{\"Username\":squatch \"password\":tarren23}";
            URLConnection boxrec = boxrecURL.openConnection();
            boxrec.setDoInput(true);
            boxrec.setDoOutput(true);

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
            pw.write(payload);
            pw.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(boxrec.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                in.close();
            }
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.TOP | Gravity.LEFT,0,0);
            toast.makeText(MainActivity.this, "In Try", toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            System.out.print(e);
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.TOP | Gravity.LEFT,0,0);
            toast.makeText(MainActivity.this, e.getMessage(), toast.LENGTH_SHORT).show();
        }
        //Toast toast = new Toast(getApplicationContext());
        //toast.setGravity(Gravity.TOP | Gravity.LEFT,0,0);
        //toast.makeText(MainActivity.this, bname.getText(), toast.LENGTH_SHORT).show();

    }
}
