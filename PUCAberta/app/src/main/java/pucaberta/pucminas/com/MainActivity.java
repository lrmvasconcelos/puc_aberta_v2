package pucaberta.pucminas.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pucaberta.pucminas.com.service.RetrofitBase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitBase.getInterfaceRetrofit()
                .login("07044894658", "1995-08-19")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(inscritoResponse -> {
                    inscritoResponse.toString();
                }, error -> {
                    error.getCause();
                });
    }
}
