package ch.ak.comfortabledebtregister.methods;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import java.text.DecimalFormat;

import ch.ak.comfortabledebtregister.ResultActivity;

public class OutputText {

    private DecimalFormat df = new DecimalFormat("0.00");

    public void displayDebtPeople(Bundle params, SharedPreferences preferences,
                                  LinearLayout debtorsList, ResultActivity resultActivity) {
        if (params != null) {
            String name = params.getString("name");
            Double debt = params.getDouble("debt");
            debt = Math.round(debt * 100.0) / 100.0;
            String oldDebtors = preferences.getString("debtors", "");
            oldDebtors = oldDebtors + "Name: " + name + "\n" + "CHF: " + df.format(debt) + ";";
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("debtors", oldDebtors);
            editor.apply();
        }
        String newDebtors = preferences.getString("debtors", "");
        String[] debtorArray = newDebtors.split(";");
        System.out.println(debtorArray.length);
        if (newDebtors.length() < 1) {
            TextView textView = new TextView(resultActivity);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18F);
            textView.setText("Keine Schuldner vorhanden.");
            debtorsList.addView(textView);
        } else {
            for(int i = debtorArray.length; i > 0; i--) {
                TextView textView = new TextView(resultActivity);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(18F);
                textView.setText(debtorArray[i - 1]);
                Space space = new Space(resultActivity);
                space.setMinimumHeight(30);
                debtorsList.addView(space);
                debtorsList.addView(textView);
            }
        }
    }

}
