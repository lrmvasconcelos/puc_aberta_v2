package pucaberta.pucminas.com.helper;

import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

import pucaberta.pucminas.com.custom.CustomDatePicker;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class DataBinder {

    @BindingAdapter({"android:visibility"})
    public static void setVisibility(View view, boolean isVisible) {

        int visibility = isVisible ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }

    @BindingAdapter("underlineColor")
    public static void setUnderlineColor(EditText editText, @ColorInt int color) {
        editText.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
    
    @BindingAdapter("maxToday")
    public static void maxToday(CustomDatePicker customDatePicker, boolean temp){

        if(temp) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            customDatePicker.getDatePickerDialog().setMaxDate(calendar);
        }
    }

    @BindingAdapter({"mask"})
    public static void setMask(EditText editText, TextWatcher textWatcher) {
        if (textWatcher != null) {
            editText.removeTextChangedListener(textWatcher);
        }
        editText.addTextChangedListener(textWatcher);


    }
}
