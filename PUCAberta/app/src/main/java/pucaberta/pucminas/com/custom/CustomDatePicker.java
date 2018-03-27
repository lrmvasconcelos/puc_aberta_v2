package pucaberta.pucminas.com.custom;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static pucaberta.pucminas.com.helper.Utils.getActivityFromContext;

/**
 * Created by lucas on 17/10/17.
 */



public class CustomDatePicker extends AppCompatEditText implements View.OnClickListener, DatePickerDialog.OnDateSetListener {


    protected static String DATE_FORMAT = "dd/MM/yyyy";
    protected final Calendar calendar = Calendar.getInstance();
    protected String selectedValue;
    protected DatePickerDialog datePickerDialog;

    public CustomDatePicker(Context context, AttributeSet attrs) {

        super(context, attrs);
        setOnClickListener(this);
        setFocusable(false);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = DatePickerDialog.newInstance(this, year, month, day);
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
        // set custom drawable
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], null, null, null);
    }
    public static String formatDate(Date date) {
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(date);
    }
    @Override
    public void onClick(View v) {
        showDialogValues();
    }
    public void showDialogValues() {
        Activity activity = getActivityFromContext(getContext());
        if (activity == null)
            throw new IllegalStateException(
                    "Could not find activity from context in CustomDatePicker");
        FragmentManager fragmentManager = activity.getFragmentManager();
        datePickerDialog.show(fragmentManager, "DatePickerDialog");
    }
    public DatePickerDialog getDatePickerDialog() {
        return datePickerDialog;
    }
    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        selectedValue = formatDate(calendar.getTime());
        setText(selectedValue);
    }
}
