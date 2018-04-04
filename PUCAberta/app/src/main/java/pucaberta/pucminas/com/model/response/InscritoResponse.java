package pucaberta.pucminas.com.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lucas on 23/03/2018.
 * Update at 23/03/2018
 */

public class InscritoResponse {

    @SerializedName("inscrito")
    @Expose
    private Inscrito inscrito;
    @SerializedName("palestras")
    @Expose
    private Palestra palestras = null;
    @SerializedName("cursos")
    @Expose
    private Curso cursos = null;

    public Inscrito getInscrito() {
        return inscrito;
    }

    public void setInscrito(Inscrito inscrito) {
        this.inscrito = inscrito;
    }

    public Palestra getPalestras() {
        return palestras;
    }

    public void setPalestras(Palestra palestras) {
        this.palestras = palestras;
    }

    public Curso getCursos() {
        return cursos;
    }

    public void setCursos(Curso cursos) {
        this.cursos = cursos;
    }

}
