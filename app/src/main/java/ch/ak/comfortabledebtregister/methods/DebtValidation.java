package ch.ak.comfortabledebtregister.methods;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ak.comfortabledebtregister.ResultActivity;

public class DebtValidation {

    private String errorMessageString = "Name ist zu kurz(<4), zu lang(>40), oder enthält unerlaubte Zeichen.\n" +
            "Oder der Betrag ist zu klein oder über 1M.";

    public boolean checkIfInputIsValid(String name, double debt) {
        String regex = "^[A-Za-z\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        boolean valid = matcher.matches();
        if (valid && name.length() <= 40
                && debt <= 1000000.0 && debt > 0
                && name.length() >= 4
        ) {
            return true;
        }
        return false;
    }

    public ArrayList<String> convertInput(EditText nameInput, EditText debtInput, TextView errorMessage) {
        String name = String.valueOf(nameInput.getText());
        String stringDebt = String.valueOf(debtInput.getText());
        if (stringDebt.length() < 1) {
            stringDebt = "0.0";
        }
        ArrayList<String> converted = new ArrayList<String>(Arrays.asList());
        converted.add(name);
        converted.add(stringDebt);
        return converted;
    }

    public Intent resetAndRegister(Intent intent, ArrayList<String> values,
                                   EditText nameInput, EditText debtInput, TextView errorMessage) {
        intent.putExtra("name", values.get(0));
        intent.putExtra("debt", Double.valueOf(values.get(1)));
        resetNames(nameInput, debtInput, errorMessage);
        return intent;
    }

    public void resetNames(EditText nameInput, EditText debtInput, TextView errorMessage) {
        nameInput.setText("");
        errorMessage.setText("");
        debtInput.setText("");
    }

    public void setErrorMessage(TextView errorMessage) {
        errorMessage.setText(errorMessageString);
    }

}
