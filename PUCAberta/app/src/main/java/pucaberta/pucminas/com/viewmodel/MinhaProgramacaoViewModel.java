package pucaberta.pucminas.com.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.genius.groupie.GroupAdapter;

import java.util.List;

import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.helper.CPFCNPJMask;
import pucaberta.pucminas.com.helper.DataMask;
import pucaberta.pucminas.com.helper.Utils;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.item.ItemMinhaProgramacao;
import pucaberta.pucminas.com.item.ItemProgrammig;
import pucaberta.pucminas.com.model.CardContent;
import pucaberta.pucminas.com.model.response.Palestra;
import pucaberta.pucminas.com.service.RetrofitBase;
import pucaberta.pucminas.com.ui.activity.HomeActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lucas on 12/04/2018.
 * Update at 12/04/2018
 */

public class MinhaProgramacaoViewModel extends BaseViewModel {


    public final GroupAdapter groupAdapter = new GroupAdapter();

    private CallbackBasicViewModel callback;

    public MinhaProgramacaoViewModel(CallbackBasicViewModel callback) {
        super(callback);
        this.callback = callback;
        init();
    }

    private void init(){
        RetrofitBase.getInterfaceRetrofit()
                .getPalestras(CPFCNPJMask.unmask(PucApp.getInstance().getUser().getCpf()),
                        PucApp.getInstance().getUser().getNasc())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(inscritoResponse -> {
                    hideProgress();
                    setList(inscritoResponse.getPalestras());
                }, error -> {
                    hideProgress();
                    PucApp.getInstance().setIsLogged(false);
                    showError(error, (dialog, which) -> dialog.dismiss());
                });
    }

    private void setList(List<Palestra> list){
        groupAdapter.clear();
        groupAdapter.add(new ItemMinhaProgramacao(callback, list));
    }
}
