package com.example.de_cell;

public class Linear {

    private String remitente;
    private String destinatario;
    private String saldo;

    public Linear() {
    }

    public Linear(String remitente, String destinatario, String saldo) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.saldo = saldo;
    }


    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
