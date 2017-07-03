package utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dcaceres.project.R;

import java.util.List;

import model.Contacto;

/**
 * Created by doris on 30/06/2017.
 */

public class AdapterContacto extends ArrayAdapter<Contacto> {

    public AdapterContacto(Context context, List<Contacto> contactos) {
        super(context, R.layout.layout_contacto, contactos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.layout_contacto, null, true);

        View vista = convertView;

        TextView nombres = (TextView)vista.findViewById(R.id.contactoTV_nombres);
        TextView ocupacion = (TextView)vista.findViewById(R.id.contactoTV_ocupacion);

        Contacto contacto = getItem(position);
        nombres.setText(contacto.getIdUsuario1());
        ocupacion.setText(contacto.getLugar());
        return  vista;

    }
}
