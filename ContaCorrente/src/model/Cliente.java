/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author alexandre.torres
 */
@Entity
public class Cliente {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private int cpf;
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ContaCorrente contaCorrente;

    public Cliente() {
    }
    
    
    public Cliente( String nome, int cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public String toString(){
        return "id: " + id + " - " + nome + " " + cpf;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    
}
