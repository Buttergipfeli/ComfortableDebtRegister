package ch.ak.comfortabledebtregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ak.comfortabledebtregister.methods.DebtValidation;
import ch.ak.comfortabledebtregister.provider.SensorEventDelegator;
import ch.ak.comfortabledebtregister.provider.SensorProvider;

public class MainActivity extends AppCompatActivity implements SensorEventDelegator {

    private SensorProvider sensorProvider;
    private DebtValidation debtValidation = new DebtValidation();
    private EditText nameInput;
    private EditText debtInput;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = (EditText) findViewById(R.id.nameInput);
        debtInput = (EditText) findViewById(R.id.debtInput);
        errorMessage = (TextView) findViewById(R.id.errorMessage);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorProvider = new SensorProvider(this);
        sensorProvider.passSensorManager(sensorManager);

        Button show = (Button) findViewById(R.id.showButton);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                nameInput.setText("");
                errorMessage.setText("");
                debtInput.setText("");
                startActivity(intent);
            }
        });

        Button register = (Button) findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> values = debtValidation.convertInput(nameInput, debtInput, errorMessage);
                if (debtValidation.checkIfInputIsValid(values.get(0), Double.valueOf(values.get(1)))) {
                    Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                    intent.putExtra("name", values.get(0));
                    intent.putExtra("debt", Double.valueOf(values.get(1)));
                    nameInput.setText("");
                    errorMessage.setText("");
                    debtInput.setText("");
                    startActivity(intent);
                } else {
                    errorMessage.setText("Name ist zu kurz(<4), zu lang(>40), oder enthält unerlaubte Zeichen.\n" +
                            "Oder der Betrag ist zu klein oder über 1M.");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorProvider.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorProvider.onResume();
    }

    @Override
    public void onAcceleratorChange(boolean phoneFlipped) {
        if (phoneFlipped) {
            ArrayList<String> values = debtValidation.convertInput(nameInput, debtInput, errorMessage);
            if (debtValidation.checkIfInputIsValid(values.get(0), Double.valueOf(values.get(1)))) {
                Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                intent.putExtra("name", values.get(0));
                intent.putExtra("debt", Double.valueOf(values.get(1)));
                nameInput.setText("");
                errorMessage.setText("");
                debtInput.setText("");
                startActivity(intent);
            } else {
                errorMessage.setText("Name ist zu kurz(<4), zu lang(>40), oder enthält unerlaubte Zeichen.\n" +
                        "Oder der Betrag ist zu klein oder über 1M.");
            }
        }
    }
}