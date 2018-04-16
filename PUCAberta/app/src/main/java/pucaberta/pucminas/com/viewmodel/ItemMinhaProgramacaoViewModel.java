package pucaberta.pucminas.com.viewmodel;

import android.databinding.ObservableField;

import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.helper.Utils;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.model.response.Palestra;
import pucaberta.pucminas.com.ui.activity.HomeActivity;

import static pucaberta.pucminas.com.app.Constants.COURSE;

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
        formatData(palestra.getPalestraData());
        unidade.set(palestra.getUnidade());
        espaco.set(palestra.getPalestraEspaco());
    }

    private void formatData(String inputData){
        String[] tempData = inputData.split(" ");

        String data = Utils.formatDataToShow(tempData[0]);
        String time = tempData[1];

        this.data.set(data+" - "+formatTime(time));

    }

    private String formatTime(String inputTime){
        String[] time = inputTime.split(":");

        return time[0]+":"+time[1];
    }

    public void loadMap(){
        PucApp.prefs.setValue(COURSE, palestraHead.get());
        openActivityNewTask(HomeActivity.class);
    }
}
