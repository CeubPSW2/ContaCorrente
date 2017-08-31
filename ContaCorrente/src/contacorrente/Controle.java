/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacorrente;

import model.Cliente;
import model.ContaCorrente;
import model.Movimentacao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alexandre.torres
 */
public class Controle {
    public static void main(String[] args) {
        SessionFactory sf = null;
        Session session = null;
        
        ContaCorrente cc = new ContaCorrente(2387);
        Cliente a = new Cliente("Antonio", 321654);
        a.setContaCorrente(cc);
        
        cc.adicionaLancamento( new Movimentacao( "C", "Depósito inicial", 1000));
        cc.adicionaLancamento(new Movimentacao("C", "Mesada", 200));
        cc.adicionaLancamento( new Movimentacao("D", "Faca do CS", 1800));
        
        
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            for( Movimentacao m : cc.getLancamentos()){
                session.save( m );
            }
            session.save( a);
            session.save(cc);
            session.beginTransaction();
            // salvar objetos
            
            session.getTransaction().commit();
            
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally{
            session.close();
            sf.close();
        }
        
        System.out.println("Seu saldo é " + cc.getSaldo());
        
    }
    
}
