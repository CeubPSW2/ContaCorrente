/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import contacorrente.HibernateUtil;
import java.util.List;
import model.Cliente;
import model.ContaCorrente;
import model.Credito;
import model.Debito;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alexandre.torres
 */
public class ClienteService {
    public static boolean insere( Cliente cliente) {
        SessionFactory sf = null;
        Session session = null;
        boolean sucesso = false;
        
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            session.beginTransaction();
            session.save( cliente);
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
    
    public static List<Cliente> getClientes(){
        SessionFactory sf = null;
        Session session = null;
        
        List<Cliente> clientes = null;
        
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            Query query = session.createQuery( "select c from Cliente c ");
            clientes = query.list();
            
            System.out.println("Lista de clientes\n------------------------");
            for (Cliente c : clientes)
                System.out.println(c);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (session != null)
                session.close();
        }
        
        return clientes;
        
    }    
    
}
