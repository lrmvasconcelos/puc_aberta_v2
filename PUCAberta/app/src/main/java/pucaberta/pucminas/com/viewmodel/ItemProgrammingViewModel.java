package pucaberta.pucminas.com.viewmodel;

import android.databinding.ObservableField;

import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.model.CardContent;

/**
 * Created by lucas on 12/04/2018.
 * Update at 12/04/2018
 */

public class ItemProgrammingViewModel extends BaseViewModel {

    public final ObservableField<CardContent> content = new ObservableField<>();
    public final ObservableField<String> head = new ObservableField<>();
    public final ObservableField<Integer> image = new ObservableField<>();

    public ItemProgrammingViewModel(CardContent content, CallbackBasicViewModel callback) {
        super(callback);
        this.content.set(content);
        setData();
    }

    private void setData(){
        head.set(content.get().getHead());
        image.set(content.get().getImage());
    }
}
