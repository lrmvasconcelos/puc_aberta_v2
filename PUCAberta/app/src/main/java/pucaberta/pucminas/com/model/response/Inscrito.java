package pucaberta.pucminas.com.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 23/03/2018.
 * Update at 23/03/2018
 */

public class Inscrito {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("cpf")
    @Expose
    private String cpf;
    @SerializedName("nasc")
    @Expose
    private String nasc;
    @SerializedName("serie")
    @Expose
    private String serie;
    @SerializedName("cnpj")
    @Expose
    private String cnpj;
    @SerializedName("escola")
    @Expose
    private String escola;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNasc() {
        return nasc;
    }

    public void setNasc(String nasc) {
        this.nasc = nasc;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

}
