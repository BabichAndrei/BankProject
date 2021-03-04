package com.bbank.bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameoperation;
    private int moneyopetation;
    private String timeoperation;
    private String cardfrom;
    private String cardto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameoperation() {
        return nameoperation;
    }

    public void setNameoperation(String nameoperation) {
        this.nameoperation = nameoperation;
    }

    public int getMoneyopetation() {
        return moneyopetation;
    }

    public void setMoneyopetation(int moneyopetation) {
        this.moneyopetation = moneyopetation;
    }

    public String getTimeoperation() {
        return timeoperation;
    }

    public void setTimeoperation(String timeoperation) {
        this.timeoperation = timeoperation;
    }

    public String getCardfrom() {
        return cardfrom;
    }

    public void setCardfrom(String cardfrom) {
        this.cardfrom = cardfrom;
    }

    public String getCardto() {
        return cardto;
    }

    public void setCardto(String cardto) {
        this.cardto = cardto;
    }
}
