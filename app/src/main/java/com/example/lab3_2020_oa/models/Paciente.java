package com.example.lab3_2020_oa.models;

public class Paciente {
    private String mail;

    private String fechaNacimiento;

    private String apellido;

    private String direccion;

    private String localidad;

    private String pacienteId;

    private String telefono;

    private String provincia;

    private String telefonoEmergencia;

    private String nombre;

    private String dni;

    public Paciente() {
    }

    public Paciente(String mail, String fechaNacimiento, String apellido, String direccion, String localidad, String pacienteId, String telefono, String provincia, String telefonoEmergencia, String nombre, String dni) {
        this.mail = mail;
        this.fechaNacimiento = fechaNacimiento;
        this.apellido = apellido;
        this.direccion = direccion;
        this.localidad = localidad;
        this.pacienteId = pacienteId;
        this.telefono = telefono;
        this.provincia = provincia;
        this.telefonoEmergencia = telefonoEmergencia;
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
