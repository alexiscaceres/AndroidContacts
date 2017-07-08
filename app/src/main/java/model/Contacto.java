package model;

/**
 * Created by doris on 30/06/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacto extends Usuario{

    @SerializedName("idUsuario1")
    @Expose
    private String idUsuario1;
    @SerializedName("idUsuario2")
    @Expose
    private String idUsuario2;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("hora")
    @Expose
    private String hora;
    @SerializedName("lugar")
    @Expose
    private String lugar;
    @SerializedName("activa")
    @Expose
    private String activa;
//    @SerializedName("idUsuario")
//    @Expose
//    private String idUsuario;
//    @SerializedName("primerNombre")
//    @Expose
//    private String primerNombre;
//    @SerializedName("segundoNombre")
//    @Expose
//    private String segundoNombre;
//    @SerializedName("primerApellido")
//    @Expose
//    private String primerApellido;
//    @SerializedName("segundoApellido")
//    @Expose
//    private String segundoApellido;
//    @SerializedName("correo")
//    @Expose
//    private String correo;
//    @SerializedName("contrasena")
//    @Expose
//    private String contrasena;
//    @SerializedName("fechaNacimiento")
//    @Expose
//    private String fechaNacimiento;
//    @SerializedName("ciudad")
//    @Expose
//    private String ciudad;
//    @SerializedName("direccion")
//    @Expose
//    private String direccion;
//    @SerializedName("numId")
//    @Expose
//    private String numId;
//    @SerializedName("tipoId")
//    @Expose
//    private String tipoId;
//    @SerializedName("telefono")
//    @Expose
//    private String telefono;
//    @SerializedName("ocupacion")
//    @Expose
//    private String ocupacion;

    public Contacto(){
    }

    public Contacto(Usuario usuario){

        super(usuario.getIdUsuario(), usuario.getPrimerNombre(), usuario.getSegundoNombre(),
              usuario.getPrimerApellido(), usuario.getSegundoApellido(), usuario.getCorreo(),
              usuario.getContrasena(), usuario.getFechaNacimiento(), usuario.getCiudad(),
              usuario.getDireccion(), usuario.getNumId(), usuario.getTipoId(), usuario.getTelefono(),
              usuario.getOcupacion());

    }

    public String getIdUsuario1() {
        return idUsuario1;
    }

    public void setIdUsuario1(String idUsuario1) {
        this.idUsuario1 = idUsuario1;
    }

    public String getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(String idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
    }

//    public String getIdUsuario() {
//        return idUsuario;
//    }
//
//    public void setIdUsuario(String idUsuario) {
//        this.idUsuario = idUsuario;
//    }
//
//    public String getPrimerNombre() {
//        return primerNombre;
//    }
//
//    public void setPrimerNombre(String primerNombre) {
//        this.primerNombre = primerNombre;
//    }
//
//    public String getSegundoNombre() {
//        return segundoNombre;
//    }
//
//    public void setSegundoNombre(String segundoNombre) {
//        this.segundoNombre = segundoNombre;
//    }
//
//    public String getPrimerApellido() {
//        return primerApellido;
//    }
//
//    public void setPrimerApellido(String primerApellido) {
//        this.primerApellido = primerApellido;
//    }
//
//    public String getSegundoApellido() {
//        return segundoApellido;
//    }
//
//    public void setSegundoApellido(String segundoApellido) {
//        this.segundoApellido = segundoApellido;
//    }
//
//    public String getCorreo() {
//        return correo;
//    }
//
//    public void setCorreo(String correo) {
//        this.correo = correo;
//    }
//
//    public String getContrasena() {
//        return contrasena;
//    }
//
//    public void setContrasena(String contrasena) {
//        this.contrasena = contrasena;
//    }
//
//    public String getFechaNacimiento() {
//        return fechaNacimiento;
//    }
//
//    public void setFechaNacimiento(String fechaNacimiento) {
//        this.fechaNacimiento = fechaNacimiento;
//    }
//
//    public String getCiudad() {
//        return ciudad;
//    }
//
//    public void setCiudad(String ciudad) {
//        this.ciudad = ciudad;
//    }
//
//    public String getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }
//
//    public String getNumId() {
//        return numId;
//    }
//
//    public void setNumId(String numId) {
//        this.numId = numId;
//    }
//
//    public String getTipoId() {
//        return tipoId;
//    }
//
//    public void setTipoId(String tipoId) {
//        this.tipoId = tipoId;
//    }
//
//    public String getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public String getOcupacion() {
//        return ocupacion;
//    }
//
//    public void setOcupacion(String ocupacion) {
//        this.ocupacion = ocupacion;
//    }
}
