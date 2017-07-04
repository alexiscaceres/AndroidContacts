package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by doris on 3/07/2017.
 */

public class Contactos {

    @SerializedName("idUsuario2")
    @Expose
    private String idUsuario2;
    @SerializedName("primerNombre")
    @Expose
    private String primerNombre;
    @SerializedName("segundoNombre")
    @Expose
    private String segundoNombre;
    @SerializedName("primerApellido")
    @Expose
    private String primerApellido;
    @SerializedName("segundoApellido")
    @Expose
    private String segundoApellido;

    public String getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(String idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

}
