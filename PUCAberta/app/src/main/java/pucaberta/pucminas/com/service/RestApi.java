package pucaberta.pucminas.com.service;

import android.databinding.ObservableField;

import java.util.List;

import pucaberta.pucminas.com.app.Constants;
import pucaberta.pucminas.com.model.response.InscritoResponse;
import pucaberta.pucminas.com.model.response.Palestra;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public interface RestApi {

    @GET(Constants.API.INSCRITO)
    Observable<InscritoResponse> login(@Query("cpf") String cpf, @Query("nasc") String nasc);

    @GET(Constants.API.GETPALESTRAS)
    Observable<InscritoResponse> getPalestras(@Query("cpf") String cpf, @Query("nasc") String nasc);
}
