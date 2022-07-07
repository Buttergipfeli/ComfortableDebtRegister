package ch.ak.comfortabledebtregister.provider;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import ch.ak.comfortabledebtregister.MainActivity;
import ch.ak.comfortabledebtregister.methods.FlippChecker;

public class SensorProvider implements SensorEventListener {

    private SensorManager sensorManager;
    private SensorEventDelegator sensorEventDelegator;
    private FlippChecker flippChecker = new FlippChecker();

    public SensorProvider(SensorEventDelegator sensorEventDelegator) {
        this.sensorEventDelegator = sensorEventDelegator;
    }

    public void passSensorManager(SensorManager sensorManagerActivity) {
        sensorManager = sensorManagerActivity;
    }

    public void onPause() {
        sensorManager.unregisterListener(this);
    }

    public void onResume() {
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        boolean isFlipped = flippChecker.phoneFlippedDown(sensorEvent);
        sensorEventDelegator.onAcceleratorChange(isFlipped);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
