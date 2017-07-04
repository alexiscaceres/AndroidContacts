package utils;

/**
 * Created by doris on 11/06/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("estado")
    @Expose
    private Integer estado;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public ResponseLogin(){
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
