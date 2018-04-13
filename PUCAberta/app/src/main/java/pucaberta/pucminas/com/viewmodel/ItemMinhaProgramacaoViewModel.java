package pucaberta.pucminas.com.viewmodel;

import android.databinding.ObservableField;

import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.model.response.Palestra;

/**
 * Created by lucas on 12/04/2018.
 * Update at 12/04/2018
 */

public class ItemMinhaProgramacaoViewModel extends BaseViewModel {

    public final ObservableField<Palestra> palestra = new ObservableField<>();
    public final ObservableField<String> palestraHead = new ObservableField<>();
    public final ObservableField<String> data = new ObservableField<>();
    public final ObservableField<String> unidade = new ObservableField<>();
    public final ObservableField<String> espaco = new ObservableField<>();

    public ItemMinhaProgramacaoViewModel(CallbackBasicViewModel callback, Palestra palestra) {
        super(callback);
        this.palestra.set(palestra);
        palestraHead.set(palestra.getPalestra());
        data.set(palestra.getPalestraData());
        unidade.set(palestra.getUnidade());
        espaco.set(palestra.getPalestraEspaco());
    }
}
