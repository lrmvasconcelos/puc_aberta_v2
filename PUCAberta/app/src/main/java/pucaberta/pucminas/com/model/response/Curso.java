package pucaberta.pucminas.com.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 23/03/2018.
 * Update at 23/03/2018
 */

public class Curso {

    @SerializedName("palestra")
    @Expose
    private String palestra;
    @SerializedName("curso")
    @Expose
    private String curso;

    public String getPalestra() {
        return palestra;
    }

    public void setPalestra(String palestra) {
        this.palestra = palestra;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}

