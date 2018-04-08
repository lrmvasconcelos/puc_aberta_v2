package pucaberta.pucminas.com.app;

import android.app.Application;

import pucaberta.pucminas.com.helper.PreferencesManager;
import pucaberta.pucminas.com.model.response.Inscrito;

import static pucaberta.pucminas.com.app.Constants.ISLOGGED;
import static pucaberta.pucminas.com.app.Constants.USER.CNPJ;
import static pucaberta.pucminas.com.app.Constants.USER.CPF;
import static pucaberta.pucminas.com.app.Constants.USER.ESCOLA;
import static pucaberta.pucminas.com.app.Constants.USER.NASC;
import static pucaberta.pucminas.com.app.Constants.USER.NOME;
import static pucaberta.pucminas.com.app.Constants.USER.SERIE;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class PucApp extends Application {

    public static PreferencesManager prefs;
    public static Inscrito sInscrito;
    private static PucApp instance;

    public static PucApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initPrefs();
    }

    private void initPrefs() {
        PreferencesManager.initializeInstance(this);
        prefs = PreferencesManager.getInstance();
    }

    public void setIsLogged(boolean isLogged){
        prefs.setValue(ISLOGGED, isLogged);
    }

    public boolean getIsLogged(){
        return prefs.getBoolean(ISLOGGED);
    }

    public Inscrito getUser() {

        if (sInscrito == null) {

            Inscrito inscrito = new Inscrito();

            inscrito.setNome(prefs.getString(NOME));
            inscrito.setCpf(prefs.getString(CPF));
            inscrito.setNasc(prefs.getString(NASC));
            inscrito.setSerie(prefs.getString(SERIE));
            inscrito.setCnpj(prefs.getString(CNPJ));
            inscrito.setEscola(prefs.getString(ESCOLA));


            return inscrito;

        } else return sInscrito;

    }

    public void setUser(Inscrito inscrito) {

        sInscrito = inscrito;

        prefs.setValue(NOME, inscrito.getNome());
        prefs.setValue(CPF, inscrito.getCpf());
        prefs.setValue(NASC, inscrito.getNasc());
        prefs.setValue(SERIE, inscrito.getSerie());
        prefs.setValue(CNPJ, inscrito.getCnpj());
        prefs.setValue(ESCOLA, inscrito.getEscola());
    }

}
