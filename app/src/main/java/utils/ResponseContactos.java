package utils;

/**
 * Created by doris on 3/07/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import model.Contactos;

public class ResponseContactos {

    @SerializedName("contactos")
    @Expose
    private List<Contactos> contactos = null;

    public List<Contactos> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contactos> contactos) {
        this.contactos = contactos;
    }

}
