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
    private List<Palestra> palestras = null;
    @SerializedName("cursos")
    @Expose
    private List<Curso> cursos = null;

    public Inscrito getInscrito() {
        return inscrito;
    }

    public void setInscrito(Inscrito inscrito) {
        this.inscrito = inscrito;
    }

    public List<Palestra> getPalestras() {
        return palestras;
    }

    public void setPalestras(List<Palestra> palestras) {
        this.palestras = palestras;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

}
