/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacorrente;

import java.util.List;
import java.util.Scanner;
import model.Cliente;
import model.ContaCorrente;
import model.Credito;
import model.Debito;
import service.ClienteService;

/**
 *
 * @author alexandretorres
 */
public class Controle {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        boolean sair = false;
        do{
            System.out.println("O que deseja fazer?");
            System.out.println("1. Inicializar dados");
            System.out.println("2. Consultar clientes");
            System.out.println("3. Consultar lançamentos de um cliente");
            System.out.println("0. Sair");
            int opcao = teclado.nextInt();
            switch( opcao){
                case 1: inicializa();
                        break;
                case 2: consultar();
                        break;
                case 0: sair = true;
                        break;
                default: System.out.println("Opção inválida");
            }
        }while(!sair);
        
    }
    
    public static void inicializa() {
        
        // Cria os dados
        ContaCorrente cc = new ContaCorrente(2387);
        Cliente cliente1 = new Cliente("Antonio", 321654);
        cliente1.setContaCorrente(cc);
        
        cc.adicionaLancamento( new Credito( "Depósito inicial", 1000));
        cc.adicionaLancamento(new Credito("Mesada", 200));
        cc.adicionaLancamento( new Debito("Faca do CS", 1800));
        
        ClienteService.insere( cliente1 );
        
        Cliente cliente2 = new Cliente( "João da Silva", 1234567);
        ContaCorrente cc1 = new ContaCorrente( 12333 );
        cc1.adicionaLancamento( new Credito( "Depósito inicial", 1500));
        cc1.adicionaLancamento(new Debito("Gasolina", 180));
        cc1.adicionaLancamento( new Debito("Auguel", 1300));
        
        ClienteService.insere( cliente2 );        
    }    
    
    public static void consultar(){
        
        List<Cliente> clientes = ClienteService.getClientes();
            
        System.out.println("Lista de clientes\n------------------------");
        for (Cliente c : clientes)
            System.out.println(c);
    }    
    
}
