package com.example.de_cell;

import java.io.Serializable;

public class shimpmentsElements implements Serializable {
    private String colors;
    private String  plan;
    private String cargo;


    public shimpmentsElements(String colors, String plan, String cargo) {
        this.colors = colors;
        this.plan = plan;
        this.cargo = cargo;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
