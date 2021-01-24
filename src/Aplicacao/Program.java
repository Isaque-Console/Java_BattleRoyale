package Aplicacao;

import Exception.JogoException;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException, JogoException {
        while(true) { // REPETE ENQUANTO O USUÁRIO NÃO ESCOLHER A OPÇÃO ZERO.
            try {
                System.out.println(Tela.menu()); //MENSAGEM DO MENU INICIAL.
                Scanner sc = new Scanner(System.in);
                Integer opcao = sc.nextInt();

                // OPÇÕES DE 0 A 5, CASO FOR UM NÚMERO FORA DESSE INTERVALO, SERÁ LANÇADA UMA EXCEÇÃO.
                switch( opcao )
                {
                    case 0:
                        System.out.println("Encerrando o jogo!");
                        return ;
                    case 1:
                        FuncoesMenu.criarPersonagem();
                        break;
                    case 2:
                        FuncoesMenu.deletarPersonagem();
                        break;

                    case 3:
                        FuncoesMenu.editarPersonagem();
                        break;
                    case 4:
                        FuncoesMenu.exibirPersonagens();
                        break;
                    case 5:
                        Batalha.realizarBatalha();
                        break;
                    default:
                        throw new JogoException("Opção inválida!");
                }
            }
            catch (JogoException e) {
                System.out.println(e.getMessage()); // MENSAGEM PERSONALIZADA.
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            finally {
                Batalha.esvaziarVariaveis(); //ESVAZIAR AS VARIÁVEIS, PARA O CASO DE ESCOLHER BATALHAR NOVAMENTE.
            }
        }
    }
}
