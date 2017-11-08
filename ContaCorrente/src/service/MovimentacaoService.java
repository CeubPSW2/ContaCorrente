/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import contacorrente.HibernateUtil;
import model.Cliente;
import model.ContaCorrente;
import model.Credito;
import model.Debito;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Alexandre.Torres
 */
public class MovimentacaoService {
    public static boolean lancarCredito(Cliente cliente, double valor){
        SessionFactory sf = null;
        Session session = null;
        boolean sucesso = false;
        
        ContaCorrente cc = cliente.getContaCorrente();
        if (cc == null) return false;
        
        Credito credito = new Credito("Lançamento de crédito", valor);
        cc.adicionaLancamento(credito);
        
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            session.beginTransaction();
            session.save( credito );
            session.getTransaction().commit();
            
            sucesso = true;
        }catch (Exception e){
            e.printStackTrace();
            if (session != null)
                session.getTransaction().rollback();
        }finally{
            if (session != null)
                session.close();
        }
        
        return sucesso;        
    }
    
   public static boolean lancarDebito(Cliente cliente, double valor){
        SessionFactory sf = null;
        Session session = null;
        boolean sucesso = false;
        
        ContaCorrente cc = cliente.getContaCorrente();
        if (cc == null) return false;
        
        Debito debito = new Debito("Lançamento de débito", valor);
        cc.adicionaLancamento(debito);
        
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            session.beginTransaction();
            session.save( debito );
            session.getTransaction().commit();
            
            sucesso = true;
        }catch (Exception e){
            e.printStackTrace();
            if (session != null)
                session.getTransaction().rollback();
        }finally{
            if (session != null)
                session.close();
        }
        
        return sucesso;        
    }    
}
