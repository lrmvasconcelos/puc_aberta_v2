package pucaberta.pucminas.com.item;

import com.genius.groupie.Item;

import java.util.List;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.databinding.FragmentMinhaProgramacaoBinding;
import pucaberta.pucminas.com.databinding.ItemMinhaProgramacaoBinding;
import pucaberta.pucminas.com.databinding.ProgramacaoCardViewBinding;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.model.CardContent;
import pucaberta.pucminas.com.model.response.Palestra;
import pucaberta.pucminas.com.viewmodel.ItemMinhaProgramacaoViewModel;
import pucaberta.pucminas.com.viewmodel.ItemProgrammingViewModel;
import pucaberta.pucminas.com.viewmodel.MinhaProgramacaoViewModel;

/**
 * Created by lucas on 12/04/2018.
 * Update at 12/04/2018
 */

public class ItemMinhaProgramacao extends Item<ItemMinhaProgramacaoBinding> {

    private CallbackBasicViewModel callback;
    private List<Palestra> list;

    public ItemMinhaProgramacao(CallbackBasicViewModel callback, List<Palestra> list) {
        this.callback = callback;
        this.list = list;
    }



    @Override
    public int getLayout() {
        return R.layout.item_minha_programacao;
    }

    @Override
    public void bind(ItemMinhaProgramacaoBinding viewBinding, int position) {
        viewBinding.setViewModel(new ItemMinhaProgramacaoViewModel(callback, list.get(position)));
    }


    public int getItemCount() {
        return list.size();
    }
}
