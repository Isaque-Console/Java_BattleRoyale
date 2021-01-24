package Aplicacao;

import Exception.JogoException;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException, JogoException {
        while(true) {
            try {
                System.out.println(Tela.menu());
                Scanner sc = new Scanner(System.in);
                Integer opcao = sc.nextInt();

                if(opcao == 0){
                    System.out.println("Encerrando o jogo!");
                    return ;
                }
                else if(opcao == 1){
                    FuncoesMenu.criarPersonagem();
                }
                else if(opcao == 2){
                    FuncoesMenu.deletarPersonagem();
                }
                else if(opcao == 3){
                    FuncoesMenu.editarPersonagem();
                }
                else if(opcao == 4){
                    FuncoesMenu.exibirPersonagens();
                }
                else if(opcao == 5){
                    Batalha.realizarBatalha();
                }
                else{
                    throw new JogoException("Opção inválida!");
                }
                // Implementar: ler o Enter e depois limpar a tela do console;
            }
            catch (JogoException e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            finally {
                Batalha.esvaziarVariaveis();
            }
        }
    }
}
