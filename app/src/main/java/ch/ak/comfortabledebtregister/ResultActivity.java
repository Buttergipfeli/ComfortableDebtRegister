package ch.ak.comfortabledebtregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import ch.ak.comfortabledebtregister.methods.OutputText;

public class ResultActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private OutputText outputText = new OutputText();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        displayDebtPeople();
    }

    private void displayDebtPeople() {
        TextView debtorsList = (TextView) findViewById(R.id.debtors);
        outputText.displayDebtPeople(getIntent().getExtras(), preferences, debtorsList);
    }

}