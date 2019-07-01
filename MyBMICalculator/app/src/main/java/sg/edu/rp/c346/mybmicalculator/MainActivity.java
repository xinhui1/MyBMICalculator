package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    Button btnCal, btnReset;
    TextView tvDate, tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);
        btnCal = findViewById(R.id.buttonCal);
        btnReset = findViewById(R.id.buttonReset);
        tvDate = findViewById(R.id.textViewDate);
        tvBMI = findViewById(R.id.textViewBMI);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnReset.isEnabled()){
                    etWeight.setText("");
                    etHeight.setText("");
                }
            }
        });
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnCal.isEnabled()){
                    Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                    String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                            (now.get(Calendar.MONTH)+1) + "/" +
                            now.get(Calendar.YEAR) + " " +
                            now.get(Calendar.HOUR_OF_DAY) + ":" +
                            now.get(Calendar.MINUTE);
                    tvDate.setText("Last calculated date: " + datetime);

                    float sumWeight = Float.parseFloat(etWeight.getText().toString());
                    float sumHeight = (2 * (Float.parseFloat(etHeight.getText().toString())));
                    float sumBMI = (sumWeight / sumHeight);
                    String BMI = String.valueOf(sumBMI);
                    tvBMI.setText("Last Calculated BMI:" + BMI);
                }
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();

        String weight = etWeight.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = prefs.edit();
        preEdit.putString("Weight", weight);

        String height = etHeight.getText().toString();
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit1 = prefs1.edit();
        preEdit1.putString("Height", height);
    }

}
