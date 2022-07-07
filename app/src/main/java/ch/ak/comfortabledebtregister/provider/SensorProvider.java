package ch.ak.comfortabledebtregister.provider;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import ch.ak.comfortabledebtregister.MainActivity;

public class SensorProvider implements SensorEventListener {

    private SensorManager sensorManager;
    private SensorEventDelegator sensorEventDelegator;

    public void passSensorManager(SensorManager sensorManagerActivity) {
        sensorManager = sensorManagerActivity;
        sensorEventDelegator = new MainActivity();
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
        sensorEventDelegator.onAcceleratorChange(true);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
