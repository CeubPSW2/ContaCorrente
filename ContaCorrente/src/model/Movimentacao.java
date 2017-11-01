/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 *
 * @author alexandre.torres
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Movimentacao {
    @Id
    @GeneratedValue
    private int id;
    private String descricao;
    private double valor;
    @ManyToOne
    private ContaCorrente contaCorrente;
    
    public Movimentacao(){}
    
    public Movimentacao(String descricao, double valor){
        this.descricao = descricao;
        this.valor = valor;
    }

    /**
     * Obtém o valor qualificado, isto é, se for um débito
     * será um valor negativo.
     * @return double
     */
    
    public abstract double getValorQualificado();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
    
    
}
