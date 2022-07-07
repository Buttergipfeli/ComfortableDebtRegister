package ch.ak.comfortabledebtregister.methods;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class OutputText {

    private DecimalFormat df = new DecimalFormat("0.00");

    public void displayDebtPeople(Bundle params, SharedPreferences preferences,
                                  TextView debtorsList) {
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
            debtorsList.setText("Keine Schuldner vorhanden.");
        } else {
            StringBuilder builder = new StringBuilder();
            for(String d: debtorArray) {
                builder.append(d + "\n\n");
            }
            debtorsList.setText(builder.toString());
        }
    }

}
