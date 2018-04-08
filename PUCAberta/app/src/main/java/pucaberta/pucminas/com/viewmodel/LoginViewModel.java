package pucaberta.pucminas.com.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.helper.CPFCNPJMask;
import pucaberta.pucminas.com.helper.ErrorObservable;
import pucaberta.pucminas.com.helper.Utils;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.service.RetrofitBase;
import pucaberta.pucminas.com.ui.activity.HomeActivity;
import pucaberta.pucminas.com.ui.activity.RegisterActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lucas on 26/03/2018.
 * Update at 26/03/2018
 */

public class LoginViewModel extends BaseViewModel {

    public final ErrorObservable cpfError = new ErrorObservable();
    public final ErrorObservable birthdayError = new ErrorObservable();
    public ObservableField<String> cpf = new ObservableField<>();
    public ObservableField<String> birthday = new ObservableField<>();

    public LoginViewModel(CallbackBasicViewModel callback) {
        super(callback);

        titleTextToolbar.set(PucApp.getInstance().getString(R.string.login));
    }

    public void login() {

        if (checkInput()) {
            showProgress();
            RetrofitBase.getInterfaceRetrofit()
                    .login(CPFCNPJMask.unmask(cpf.get()), Utils.formatDataToApi(birthday.get()))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(inscritoResponse -> {
                        hideProgress();
                        if(!TextUtils.isEmpty(inscritoResponse.getInscrito().getNome())){
                            openActivityNewTask(HomeActivity.class);
                        }else {
                            showSimpleDialog("Oops", "Usuário não cadastrado.");
                        }
                    }, error -> {
                        hideProgress();
                        showError(error, (dialog, which) -> dialog.dismiss());
                    });
        }
    }

    public void callRegister() {
        openActivity(RegisterActivity.class);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(cpf.get()) || TextUtils.isEmpty(birthday.get())) {
            showSimpleDialog("Oops", "Todos os campos devem ser preenchidos");
            return false;
        }
        return true;
    }
}
