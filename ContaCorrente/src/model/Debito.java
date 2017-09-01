/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;

/**
 *
 * @author alexandretorres
 */
@Entity
public class Debito extends Movimentacao{
        
    public Debito(String descricao, double valor){
        super(descricao, valor);
    }
    
    @Override
    public double getValorQualificado(){
        return super.getValor()* -1;
    }
    
}
