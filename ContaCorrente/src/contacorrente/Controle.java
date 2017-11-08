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
import model.Movimentacao;
import org.hibernate.SessionFactory;
import service.ClienteService;
import service.ContaCorrenteService;
import service.MovimentacaoService;

/**
 *
 * @author alexandretorres
 */
public class Controle {
    public static void main(String[] args) {
        menuPrincipal();
//         Cliente cliente = ClienteService.getCliente(1);
//        MovimentacaoService.lancarCredito(cliente, 1000);
    }
    public static void menuPrincipal() {
        Scanner teclado = new Scanner(System.in);
        
        boolean sair = false;
        do{
            System.out.println("O que deseja fazer?");
            System.out.println("1. Inicializar dados");
            System.out.println("2. Consultar clientes");
            System.out.println("0. Sair");
            int opcao = teclado.nextInt();
            switch( opcao){
                case 1: inicializa();
                        break;
                case 2: consultarClientes();
                        break;
                case 0: sair = true;
                        break;
                default: System.out.println("Opção inválida");
            }
        }while(!sair);
        
        try {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            sf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void menuMovimentacao() {
        Scanner teclado = new Scanner(System.in);
        
        boolean sair = false;
        do{
            System.out.println("O que deseja fazer?");
            System.out.println("1. Consultar conta do cliente");
            System.out.println("2. Consultar movimentações do cliente");
            System.out.println("3. Lançar débito");
            System.out.println("4. Lançar crédito");
            System.out.println("0. voltar");
            int opcao = teclado.nextInt();
            
            switch( opcao){
                case 1: consultaConta();
                        break;
                case 2: consultaMovimentacao();
                        break;
                case 3: lancarDebito();
                        break;
                case 4: lancarCredito();
                        break;
                case 0: sair = true;
                        break;
                default: System.out.println("Opção inválida");
            }
        }while(!sair);
        
    }    
    
    public static Cliente perguntaCliente(){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o id do cliente: ");
        int id = teclado.nextInt();
        
        Cliente cliente = ClienteService.getCliente(id);
        if (cliente == null){
            System.out.println("Cliente não encontrado");
        }        
        
        return cliente;
    }
    public static void consultaConta(){
        /*
         TODO
        1 - pedir o id do cliente e recuperar o objeto
        2 - criar ContaCorrenteService.getContaCorrente(Cliente cliente)
        3 - mostrar o saldo da conta corrente
        */       

        Cliente cliente = perguntaCliente();
        if (cliente == null){            
            return;
        }
                
        System.out.println("Conta Corrente " + cliente.getContaCorrente().getNumero());
        System.out.println("Saldo R$ " + cliente.getContaCorrente().getSaldo());
    }
    
    public static void consultaMovimentacao(){
        /*
         TODO
        1 - pedir o id do cliente e recuperar o objeto
        2 - criar MovimentacaoService.getMovimentacao(Cliente cliente)
        3 - mostrar os lançamentos, destacando se débito ou crédito
        */               
        Cliente cliente = perguntaCliente();
        if (cliente == null){            
            return;
        }
        
        List<Movimentacao> movs = cliente.getContaCorrente().getLancamentos();
        for (Movimentacao mov : movs) {
            System.out.println( mov );            
        }
    }

    public static void lancarDebito(){
        /*
         TODO
        1 - pedir o id do cliente e recuperar o objeto
        2 - pedir o valor do débito
        2 - criar MovimentacaoService.lancarDebito(Cliente cliente, double valor)
        */   

        Cliente cliente = perguntaCliente();
        if (cliente == null){            
            return;
        }
        
        Scanner teclado = new Scanner(System.in);
        System.out.print("\nDigite o valor do débito: R$ ");
        double debito = teclado.nextDouble();
        
        MovimentacaoService.lancarDebito(cliente, debito);        
    }    
    
    public static void lancarCredito(){
        /*
         TODO
        1 - pedir o id do cliente e recuperar o objeto
        2 - pedir o valor do crédito
        2 - criar MovimentacaoService.lancarCredito(Cliente cliente, double valor)
        */           
                
        Cliente cliente = perguntaCliente();
        if (cliente == null){            
            return;
        }
        
        Scanner teclado = new Scanner(System.in);
        System.out.print("\nDigite o valor do credito: R$ ");
        double credito = teclado.nextDouble();
        
        MovimentacaoService.lancarCredito(cliente, credito);
        
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
    
    public static void consultarClientes(){
        
        List<Cliente> clientes = ClienteService.getClientes();
            
        System.out.println("Lista de clientes\n------------------------");
        for (Cliente c : clientes)
            System.out.println(c);
        
        menuMovimentacao();
        /* TODO
        - pegar o Id e obter o cliente
        - fazer o getMovimentacoes passando o cliente
        - 
        */
    }    
    
}
