/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacorrente;

import model.Cliente;
import model.ContaCorrente;
import model.Credito;
import model.Debito;
import model.Movimentacao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alexandre.torres
 */
public class InicializaDados {
    public static void inicializa() {
        SessionFactory sf = null;
        Session session = null;
        
        ContaCorrente cc = new ContaCorrente(2387);
        Cliente a = new Cliente("Antonio", 321654);
        a.setContaCorrente(cc);
        
        cc.adicionaLancamento( new Credito( "Depósito inicial", 1000));
        cc.adicionaLancamento(new Credito("Mesada", 200));
        cc.adicionaLancamento( new Debito("Faca do CS", 1800));
        
        Cliente b = new Cliente( "João da Silva", 1234567);
        ContaCorrente cc1 = new ContaCorrente( 12333 );
        cc1.adicionaLancamento( new Credito( "Depósito inicial", 1500));
        cc1.adicionaLancamento(new Debito("Gasolina", 180));
        cc1.adicionaLancamento( new Debito("Auguel", 1300));
        
        
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            
            session.beginTransaction();
            session.save( a);
            session.save(cc);
            session.getTransaction().commit();
            
            session.beginTransaction();
            session.save(b);
            session.save(cc1);
            session.getTransaction().commit();
            
        }catch(ExceptionInInitializerError ei ){
            System.out.println("**********");
            System.out.println("Banco de dados não inicializado");
            System.out.println("**********");
            ei.printStackTrace();
            
        }catch (Exception e){
            if (session != null)
                session.getTransaction().rollback();
        }finally{
            if (session != null)
                session.close();
            
            if (sf != null)
                sf.close();
        }
        
        System.out.println("Seu saldo é " + cc.getSaldo());
        
    }
    
}
