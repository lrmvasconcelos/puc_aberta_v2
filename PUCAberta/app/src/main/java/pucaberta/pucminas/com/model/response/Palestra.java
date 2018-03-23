package pucaberta.pucminas.com.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 23/03/2018.
 * Update at 23/03/2018
 */

public class Palestra {

    @SerializedName("palestra")
    @Expose
    private String palestra;
    @SerializedName("palestra_data")
    @Expose
    private String palestraData;
    @SerializedName("unidade")
    @Expose
    private String unidade;
    @SerializedName("palestra_espaco")
    @Expose
    private String palestraEspaco;
    @SerializedName("status_inscricao")
    @Expose
    private String statusInscricao;

    public String getPalestra() {
        return palestra;
    }

    public void setPalestra(String palestra) {
        this.palestra = palestra;
    }

    public String getPalestraData() {
        return palestraData;
    }

    public void setPalestraData(String palestraData) {
        this.palestraData = palestraData;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getPalestraEspaco() {
        return palestraEspaco;
    }

    public void setPalestraEspaco(String palestraEspaco) {
        this.palestraEspaco = palestraEspaco;
    }

    public String getStatusInscricao() {
        return statusInscricao;
    }

    public void setStatusInscricao(String statusInscricao) {
        this.statusInscricao = statusInscricao;
    }
}
