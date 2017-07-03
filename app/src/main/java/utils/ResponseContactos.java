package utils;

/**
 * Created by doris on 30/06/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import model.Contacto;

public class ResponseContactos {

    @SerializedName("contactos")
    @Expose
    private List<Contacto> contactos = null;

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

}
