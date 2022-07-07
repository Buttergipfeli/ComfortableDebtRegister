package ch.ak.comfortabledebtregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ch.ak.comfortabledebtregister.provider.SensorEventDelegator;
import ch.ak.comfortabledebtregister.provider.SensorProvider;

public class MainActivity extends AppCompatActivity implements SensorEventDelegator {

    private SensorProvider sensorProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorProvider = new SensorProvider();
        sensorProvider.passSensorManager(sensorManager);

        Button show = (Button) findViewById(R.id.showButton);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                startActivity(intent);
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
        System.out.println("Sensor");
    }
}