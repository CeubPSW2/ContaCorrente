/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacorrente;

import java.util.Scanner;

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
                case 1: InicializaDados.inicializa();
                        break;
                case 2: ConsultarClientes.consultar();
                        break;
                case 0: sair = true;
                        break;
                default: System.out.println("Opção inválida");
            }
        }while(!sair);
        
    }
    
}
