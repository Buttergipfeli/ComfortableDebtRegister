package ch.ak.comfortabledebtregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView text = (TextView) findViewById(R.id.debtors);
        text.setText("Mrk\n\nSheesh");
    }

    private void displayDebtPeople() {

    }

}