package pucaberta.pucminas.com.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


/**
 * Created by lrmvasconcelos on 05/03/2018.
 * Update at 05/03/2018 by lrmvasconcelos.
 */

public abstract class CPFCNPJMask {

    private static final String mask10 = "###.###.###-##";
    private static final String mask11 = "###.###.###-##";

    public static String unmask(String s) {
        if (s == null) return "";
        return s.replaceAll("[^0-9]*", "");
    }

    public static TextWatcher insert(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = CPFCNPJMask.unmask(s.toString());
                String mask;
                String defaultMask = getDefaultMask(str);
                switch (str.length()) {
                    case 11:
                        mask = mask10;
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    public static String getDefaultMask(String str) {
        String defaultMask = mask10;
        if (str.length() > 11) {
            defaultMask = mask11;
        }
        return defaultMask;
    }

    public static String applyCpfMask(String text) {
        String maskChars = "";
        String oldText = "";
        int i = 0;
        for (char m : mask11.toCharArray()) {
            if (m != '#' && text.length() > oldText.length()) {
                maskChars += m;
                continue;
            }
            try {
                maskChars += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return maskChars;
    }
}

