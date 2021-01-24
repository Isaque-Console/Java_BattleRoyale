package Aplicacao;

import Entities.Agua;
import Entities.Fogo;
import Entities.Personagem;
import Entities.Terra;
import Exception.JogoException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FuncoesMenu {

    // MOSTRA A MENSAGEM E CHAMA TODOS O MÉTODOS PARA QUE A CRIAÇÃO DO PERSONAGEM ACONTEÇA.
    public static void criarPersonagem() throws JogoException, IOException {
        Personagem p;

        System.out.println("==============================================");
        System.out.println("            Criando Personagem");
        System.out.println("==============================================");

        p = recebendoValores();

        // salvar as informações do personagem em um arquivo.txt(ok!)
        String dadosPersonagem = (p.getNome() + ";" + p.getTipo() + ";" + p.getPontosDeVida() + ";" + p.getAtaqueMax() + ";" + p.getDefesaMax() + ";" +p.getAgilidade() + ";" + p.getDesvantagem());
        gravarDados(dadosPersonagem);
    }

    // MOSTRA A MENSAGEM DE EDITAR,CHAMA O MÉTODO DE EDITAR O ARQUIVO E VERIFICA SE O NOME FOI EDITADO.
    public static void editarPersonagem() throws FileNotFoundException, JogoException {
        System.out.println("==============================================");
        System.out.println("           Editando Personagem");
        System.out.println("==============================================");
        System.out.print("Digite o nome do personagem que você quer editar: ");

        // FAZER O TRATAMENTO PARA CASO NÃO ENCONTRE O NOME QUE FOI PASSADO(ok!)
        if(editarArquivo()){
            System.out.println("Personagem editado!");
        }
        else{
            System.out.println("Nome não encontrado!");
        }
    }

    // BUSCA O PERSONAGEM PELO NOME, E DELETA ELE DO ARQUIVO.
    public static void deletarPersonagem() throws IOException, JogoException {
        System.out.print("digite o nome do personagem que você quer deletar: ");
        Scanner ler = new Scanner(System.in);
        String nome = ler.next();
        List<String> linhas = new ArrayList<String>();
        linhas = lerArquivoCompleto();
        boolean personagemDeletado = false;

        for (int i = 0; i < linhas.toArray().length; i++) {
            var dados = linhas.get(i).split(";");
            if(nome.intern() == dados[0].intern()){
                linhas.remove(i);
                limparArquivo();
                for (int j = 0; j < linhas.toArray().length; j++) {
                    gravarDados(linhas.get(j));
                }
                personagemDeletado = true;
                System.out.println("Personagem deletado!");
                break;
            }
        }
        if(!personagemDeletado){
            throw new JogoException("Nome não encontrado!");
        }
    }

    //LER O ARQUIVO E PRINTA OS DADOS DOS PERSOANGENS.
    public static void exibirPersonagens() throws FileNotFoundException {
        // leio o arquivo e exibo os dados
        System.out.println("==============================================");
        System.out.println("           Exibindo Personagem");
        System.out.println("==============================================");
        List<String> linhasArquivo = new ArrayList<String>();
        linhasArquivo = lerArquivoCompleto();
        for (int i = 0; i < linhasArquivo.toArray().length; i++) {
            var atributos = linhasArquivo.get(i).split(";") ;
            System.out.println("---------------Personagem " + atributos[0] +"---------------\n"
                    +"Tipo: "  + atributos[1] + "\nVida: "  + atributos[2] + "\nAtaque: " + atributos[3] +
                    "\nDefesa: " + atributos[4] + "\nAgilidade: " + atributos[5] + "\nDesvantagem: " + atributos[6]);
        }
    }


    //Ler o arquivo e retorna um ArrayList contendo uma linha do arquivo em cada variável(ok!).
    public static List<String> lerArquivoCompleto() throws FileNotFoundException {
        List<String> arquivoCompleto = new ArrayList<String>();
        File caminho = new File("Arquivo_Personagens.txt");
        Scanner lerArquivo = null;
        try {
            lerArquivo = new Scanner(caminho);
            FileReader fr = new FileReader(caminho);
            while (lerArquivo.hasNextLine()) {
                String linha = lerArquivo.nextLine();
                arquivoCompleto.add(linha);
            }
        }
        catch (FileNotFoundException e) {
            e.getMessage();
        }
        return arquivoCompleto;
    }

    // INTERAGE COM O USUÁRIO PARA ELE DISTRIBUIR 10 PONTOS ENTRE OS ATRIBUTOS, E PARA DAR O NOME DO PERSONAGEM.
    public static Personagem recebendoValores() throws JogoException, FileNotFoundException {
        int pontos = 10;
        Personagem p = instanciandoPersonagem();
        Scanner sc = new Scanner(System.in);

        System.out.print("Qual o nome do personagem? ");
        String nome = sc.next();
        nomeInedito(nome);//tratando o caso de ter personagens com os nomes iguais na parte de criacao do personagem(ok!).

        p.setNome(nome);//atribuo o nome ao personagem(ok!)
        System.out.println("OBS: O personagem já têm 1 ponto de vida.");
        while(pontos > 0){
            System.out.println("Distribua os " + pontos + " pontos entre os atributos:");
            System.out.println(" 1- ataque\n 2- defesa\n 3- vida \n 4- agilidade");
            Scanner s1 = new Scanner(System.in);
            int atributo = s1.nextInt();
            if (1 <= atributo && atributo <= 4) { // testar se a opcao é válida(ok!)
                if(atributo == 1) {
                    p.setAtaqueMax(p.getAtaqueMax() + 1);//incrementa em 1 o ataque do personagem (ok!)
                    System.out.println("Acrescentado 1 ponto no atributo de ataque!");
                }
                else if(atributo == 2){
                    p.setDefesaMax(p.getDefesaMax() + 1);//incrementa em 1 a defesa do personagem (ok!)
                    System.out.println("Acrescentado 1 ponto no atributo de defesa!");
                }
                else if(atributo == 3){
                    p.setPontosDeVida(p.getPontosDeVida() + 1);//incrementa em 1 a vida do personagem (ok!)
                    System.out.println("Acrescentado 1 ponto no atributo de vida");
                }
                else if(atributo == 4){
                    p.setAgilidade(p.getAgilidade() + 1);//incrementa em 1 a agilidade do personagem (ok!)
                    System.out.println("Acrescentado 1 ponto no atributo de agilidade");
                }
                pontos = pontos - 1;//decrementa em 1 os pontos a serem distribuidos (ok!)
            }
            //
            else {
                throw new JogoException("Opção inválida!");
            }
        }
        return p;
    }

    // INSTANCIA UM PERSONAGEM DE ACORDO COM O TIPO QUE O USUÁRIO ESCOLHEU.
    public static Personagem instanciandoPersonagem() throws JogoException {
        Personagem p;
        Scanner sc = new Scanner(System.in);

        System.out.print("Qual o tipo do personagem que será criado?(Fogo/Terra/Água) ");//instanciando o personagem de acordo com o tipo dele(ok!)
        String tipo = sc.next();
        if(tipo.intern() == "Fogo" || tipo.intern() == "fogo"){
            p = new Fogo("Fogo");
            p.setDesvantagem("Agua");
        }
        else if(tipo.intern() == "Terra" || tipo.intern() == "terra"){
            p = new Terra("Terra");
            p.setDesvantagem("Fogo");

        }
        else if(tipo.intern() == "Água" || tipo.intern() == "água" || tipo.intern() == "Agua" || tipo.intern() == "agua"){
            p = new Agua("Agua");
            p.setDesvantagem("Terra");

        }
        else{
            throw new JogoException("Tipo inválido!");
        }
        return p;
    }

    // ESCREVE OS DADOS NO ARQUIVO.
    public static void gravarDados(String dados) throws IOException {
        String caminho = "Arquivo_Personagens.txt";
        FileWriter fw = new FileWriter( caminho, true );
        BufferedWriter bw = new BufferedWriter( fw );
        bw.append(dados + "\r\n");
        bw.close();
    }

    //VERIFICA NO ARQUIVO SE O NOME ESCOLHIDO JÁ ESTÁ SENDO USADO POR UM PERSONAGEM.
    public static void nomeInedito(String nome) throws FileNotFoundException, JogoException {
        List<String> linhas = new ArrayList<String>();
        linhas = lerArquivoCompleto();
        for (int i = 0; i < linhas.toArray().length; i++) {
            var dadosDoArquivo = linhas.get(i).split(";");
            if(dadosDoArquivo[0].intern() == nome.intern()){
                throw new JogoException("Esse nome já está sendo usado por um personagem!");
            }
        }
    }

    // DEIXA O ARQUIVO EM BRANCO,PARA PODER SER ESCRITO NOVAMENTE COM OUTROS DADOS.
    public static void limparArquivo() throws IOException {
        File caminho = new File("Arquivo_Personagens.txt");
        Writer clean = new BufferedWriter(new FileWriter(caminho));
        clean.close();
    }

    // EDITA O ARQUIVO, MUDANDO O NOME DO PERSONAGEM.
    public static boolean editarArquivo() throws FileNotFoundException, JogoException {
        Scanner lerNome = new Scanner(System.in);
        String nome = lerNome.next();
        List<String> arquivoCompleto = lerArquivoCompleto();

        try{
            for(int k = 0; k < arquivoCompleto.toArray().length;k++){
                var arquivoDados = arquivoCompleto.get(k).split(";");
                if(arquivoDados[0].intern() == nome.intern()){
                    System.out.print("Digite o nome que você quer colocar: ");
                    String novoNome = lerNome.next();
                    nomeInedito(novoNome);//tratando o caso de ter personagens com os nomes iguais na parte de edicao do personagem(ok!).
                    arquivoDados[0] = novoNome;
                    String dadoAtualizado = arquivoDados[0] + ";" + arquivoDados[1] + ";" + arquivoDados[2] + ";" + arquivoDados[3] +
                            ";" + arquivoDados[4] + ";" + arquivoDados[5] + ";" + arquivoDados[6];
                    arquivoCompleto.set(k, dadoAtualizado); // A posição que armazenava o antigo nome, agora armazena o novo nome(ok!);
                    limparArquivo();

                    // Escrevo o arquivo por completo, atualizando o nome do personagem(ok!).
                    String dados;
                    for(int j = 0;j<arquivoCompleto.toArray().length;j++){
                        dados = arquivoCompleto.get(j);
                        gravarDados(dados);
                    }
                    return true;
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}


