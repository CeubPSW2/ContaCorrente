/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacorrente;

import java.util.List;
import model.Cliente;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alexandretorres
 */
public class ConsultarClientes {
    public static void consultar(){
        SessionFactory sf = null;
        Session session = null;
        
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            Query query = session.createQuery( "select c from Cliente c ");
            List<Cliente> clientes = query.list();
            
            System.out.println("Lista de clientes\n------------------------");
            for (Cliente c : clientes)
                System.out.println(c);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (session != null)
                session.close();
            
            if (sf != null)
                sf.close();
        }

        
    }
    
}
