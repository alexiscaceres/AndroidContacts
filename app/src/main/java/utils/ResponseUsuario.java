package utils;

import model.Usuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by doris on 7/07/2017.
 */

public class ResponseUsuario {

    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}


