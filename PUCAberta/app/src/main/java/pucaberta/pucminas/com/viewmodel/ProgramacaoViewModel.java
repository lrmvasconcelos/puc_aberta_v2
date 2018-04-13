package pucaberta.pucminas.com.viewmodel;

import com.genius.groupie.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.item.ItemProgrammig;
import pucaberta.pucminas.com.model.CardContent;

/**
 * Created by lucas on 12/04/2018.
 * Update at 12/04/2018
 */

public class ProgramacaoViewModel extends BaseViewModel {

    public final GroupAdapter groupAdapter = new GroupAdapter();

    private List<CardContent> mList;

    private CallbackBasicViewModel callbackBasicViewModel;

    public ProgramacaoViewModel(CallbackBasicViewModel callback) {
        super(callback);

        callbackBasicViewModel = callback;

        initializeData();

        setList();
    }

    private void initializeData() {
        mList = new ArrayList<>();

        mList.add(new CardContent("GRUPO A - TEATRO - PRÉDIO 30 –\n" +
                "IPUC - 13h30 às 15h30", R.drawable.grupo_a));

        mList.add(new CardContent("GRUPO B - AUDITÓRIO 1 - PRÉDIO 4 -\n" +
                "FMD - 13h30 às 15h30", R.drawable.grupo_b));

        mList.add(new CardContent("GRUPO C - AUDITÓRIO 2 - PRÉDIO 5 –\n" +
                "ICEG - 13h30 às 15h30", R.drawable.grupo_c));

        mList.add(new CardContent("GRUPO D - AUDITÓRIO 3 - PRÉDIO 43 –\n" +
                "ICBS - 13h30 às 15h30", R.drawable.grupo_d));

        mList.add(new CardContent("GRUPO E - AUDITÓRIO MULTIUSO - PRÉDIO 43 –\n" +
                "ICH - 13h30 às 15h30", R.drawable.grupo_e));

        mList.add(new CardContent("GRUPO F - TEATRO - PRÉDIO 30 –\n" +
                "ICBS - 16h às 18h", R.drawable.grupo_f));

        mList.add(new CardContent("GRUPO G - AUDITÓRIO 1 - PRÉDIO 4 -\n" +
                "FAPSI - 16h às 18h", R.drawable.grupo_g));

        mList.add(new CardContent("GRUPO H - AUDITÓRIO 2 PRÉDIO 5 -\n" +
                "FCA - 16h às 18h", R.drawable.grupo_h));

        mList.add(new CardContent("GRUPO I - AUDITÓRIO 3  - PRÉDIO 43 -\n" +
                " ICS  - 16h às 18h", R.drawable.grupo_i));

        mList.add(new CardContent("GRUPO J - AUDITÓRIO MULTIUSO - PRÉDIO 43 – \n" +
                "ICEI - 16h às 18h", R.drawable.grupo_j));


    }

    private void setList(){
        groupAdapter.clear();
        groupAdapter.add(new ItemProgrammig(callbackBasicViewModel, mList));
    }
}
