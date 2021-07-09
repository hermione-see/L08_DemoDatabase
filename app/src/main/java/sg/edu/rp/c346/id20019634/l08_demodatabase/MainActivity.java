package sg.edu.rp.c346.id20019634.l08_demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTask;
    TextView tvResult;
    EditText etTask, etDate;
    ListView lvTask;

    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnGetTask = findViewById(R.id.buttonGetTask);
        tvResult = findViewById(R.id.textViewResult);
        etDate = findViewById(R.id.editTextDate);
        etTask = findViewById(R.id.editTextTask);
        lvTask = findViewById(R.id.lv);

        alTasks = new ArrayList<String>();
        aaTasks = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTask.setAdapter(aaTasks);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etTask.getText().toString(), etDate.getText().toString());

            }
        });

        btnGetTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                alTasks.clear();
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<Task> data = db.getTasks();
                db.close();

                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    alTasks.add(data.get(i).toString());
                }
                aaTasks.notifyDataSetChanged();
            }
        });


    }

}