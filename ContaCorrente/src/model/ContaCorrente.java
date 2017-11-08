/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 *
 * @author alexandre.torres
 */
@Entity
public class ContaCorrente {
    @Id
    @GeneratedValue
    private int id;
    private int numero;
    @OneToMany( mappedBy="contaCorrente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Movimentacao> lancamentos = new ArrayList<>();

    public ContaCorrente() {
    }

    
    public double getSaldo(){
        double saldo = 0;
        
        for (Movimentacao m : lancamentos){
            saldo += m.getValorQualificado(); // chamada polimórfica
        }
        
        return saldo;  
    }
    
    public List<Movimentacao> getLancamentos() {
        return lancamentos;
    }

    public void adicionaLancamento( Movimentacao m){
        lancamentos.add(m);
        m.setContaCorrente(this);
    }
    public void setLancamentos(List<Movimentacao> lancamentos) {
        this.lancamentos = lancamentos;
    }

    
    public ContaCorrente( int numero){
        this.numero = numero;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
}
