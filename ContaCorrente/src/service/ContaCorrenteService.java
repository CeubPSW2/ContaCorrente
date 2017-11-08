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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Alexandre.Torres
 */
public class ContaCorrenteService {
    
    public static ContaCorrente getContaCorrente(Cliente cliente){
        SessionFactory sf = null;
        Session session = null;
        ContaCorrente cc = null;
        
        String hql = "FROM ContaCorrente ";
        //hql +=       "WHERE ContaCorrente.Cliente = :cliente";
        
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            Query query = session.createQuery( hql );
            query.setParameter("cliente", cliente);
            
            List<ContaCorrente> contas = query.list();
            if (contas.size() > 0 )
                cc = contas.get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (session != null)
                session.close();
        }
        
        return cc;        
    }
    
}
