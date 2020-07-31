package com.example.lab3_2020_oa.models;

import android.content.ClipData;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class VisitaDTO {
    private String fecha;

    private long visitaId;

    private String notaEmpleado;

    private Paciente paciente;

    private Date empleadoConfirmacion;

    private String notaOficina;

    private String numeroItem;

    private ItemDTO itemDTO;

    private byte[] foto;

    private byte[] Firma;


    public VisitaDTO(String fecha, long visitaId, String notaEmpleado, Paciente paciente, Date empleadoConfirmacion, String notaOficina, String numeroItem, ItemDTO itemDTO, byte[] foto, byte[] firma) {
        this.fecha = fecha;
        this.visitaId = visitaId;
        this.notaEmpleado = notaEmpleado;
        this.paciente = paciente;
        this.empleadoConfirmacion = empleadoConfirmacion;
        this.notaOficina = notaOficina;
        this.numeroItem = numeroItem;
        this.itemDTO = itemDTO;
        this.foto = foto;
        Firma = firma;
    }

    public byte[] getFirma() {
        return Firma;
    }

    public void setFirma(byte[] firma) {
        Firma = firma;
    }

    public VisitaDTO() {

    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(long visitaId) {
        this.visitaId = visitaId;
    }

    public String getNotaEmpleado() {
        return notaEmpleado;
    }

    public void setNotaEmpleado(String notaEmpleado) {
        this.notaEmpleado = notaEmpleado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getEmpleadoConfirmacion() {
        return empleadoConfirmacion;
    }

    public void setEmpleadoConfirmacion(Date empleadoConfirmacion) {
        this.empleadoConfirmacion = empleadoConfirmacion;
    }

    public String getNotaOficina() {
        return notaOficina;
    }

    public void setNotaOficina(String notaOficina) {
        this.notaOficina = notaOficina;
    }

    public String getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(String numeroItem) {
        this.numeroItem = numeroItem;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }
}
