package pucaberta.pucminas.com.item;

import com.genius.groupie.Item;

import java.util.List;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.databinding.ProgramacaoCardViewBinding;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.model.CardContent;
import pucaberta.pucminas.com.viewmodel.ItemProgrammingViewModel;

/**
 * Created by lucas on 12/04/2018.
 * Update at 12/04/2018
 */

public class ItemProgrammig extends Item<ProgramacaoCardViewBinding> {

    private CallbackBasicViewModel callback;
    private List<CardContent> list;

    public ItemProgrammig(CallbackBasicViewModel callback, List<CardContent> list) {
        this.callback = callback;
        this.list = list;
    }


    @Override
    public int getLayout() {
        return R.layout.programacao_card_view;
    }

    @Override
    public void bind(ProgramacaoCardViewBinding viewBinding, int position) {
        viewBinding.setViewModel(new ItemProgrammingViewModel(list.get(position), callback));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
