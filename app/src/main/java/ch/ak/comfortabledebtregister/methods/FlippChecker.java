package ch.ak.comfortabledebtregister.methods;

import android.hardware.SensorEvent;

public class FlippChecker {

    public boolean phoneFlippedDown(SensorEvent sensorEvent) {
        float y = sensorEvent.values[1];
        if (y <= -8F) {
            return true;
        }
        return false;
    }

}
