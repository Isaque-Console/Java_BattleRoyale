package Aplicacao;

import Entities.Agua;
import Entities.Fogo;
import Entities.Personagem;
import Entities.Terra;
import Exception.JogoException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static Aplicacao.FuncoesMenu.lerArquivoCompleto;

public class Batalha {

    private static List<Personagem> personagensBatalhando = new ArrayList<Personagem>(); // PERSONAGENS COM VIDA MAIOR QUE ZERO.
    private static List<Personagem> naoSorteados = new ArrayList<Personagem>(); // PERSONAGENS QUE AINDA SERÃO SORTEADOS PARA EXECUTAR ALGUMA AÇÃO.
    private static Integer turno = 0;



    // GET
    public static List<Personagem> getPersonagensBatalhando() {
        return personagensBatalhando;
    }

    //SET
    public static void setPersonagensBatalhando(List<Personagem> personagensBatalhando) {
        Batalha.personagensBatalhando = personagensBatalhando;
    }

    //GET
    public static List<Personagem> getNaoSorteados() {
        return naoSorteados;
    }

    //SET
    public static void setNaoSorteados(List<Personagem> lista) {
        for (int i = 0; i < lista.toArray().length; i++) {
            Batalha.naoSorteados.add(lista.get(i));
        }
    }

    //GET
    public static Integer getTurno() {
        return turno;
    }

    //SET
    public static void setTurno(Integer turno) {
        Batalha.turno = turno;
    }

    //personagensBatalhando recebe todos os personagens do arquivo.
    private static void carregarPersonagens() throws FileNotFoundException {
        List<String> arquivoCompleto = lerArquivoCompleto();
        for (int i = 0; i < arquivoCompleto.toArray().length; i++) {
            Personagem p;
            var atributos = arquivoCompleto.get(i).split(";") ;
            if(atributos[1].intern() == "Fogo"){
                p = new Fogo("Fogo");
                p.setDesvantagem("Agua");
            }
            else if(atributos[1].intern() == "Agua"){
                p = new Agua("Agua");
                p.setDesvantagem("Terra");
            }
            else{
                p = new Terra("Terra");
                p.setDesvantagem("Fogo");
            }
            p.setNome(atributos[0]);
            p.setPontosDeVida(Integer.parseInt(atributos[2]));
            p.setAtaqueMax(Integer.parseInt(atributos[3]));
            p.setDefesaMax(Integer.parseInt(atributos[4]));
            p.setAgilidade(Integer.parseInt(atributos[5]));
            personagensBatalhando.add(p);
        }
    }

    //CONTÉM TODOS OS MÉTODOS NECESSÁRIOS PARA REALIZAR A BATALHA
    public static void realizarBatalha() throws JogoException, FileNotFoundException {
        mensagemIniciarBatalha();
        carregarPersonagens();
        while(getPersonagensBatalhando().toArray().length > 1) {
            setTurno(getTurno() + 1);//soma +1 ao turno. O turno é incrementado quando todos os personagens executam sua ação.
            mensagemNovoTurno();
            usarHabilidadeEspecial(); // USADA APENAS DE 4 EM 4 TURNOS.
            zerarPontosDeDefesa();
            setNaoSorteados(personagensBatalhando);
            while (getNaoSorteados().toArray().length > 0) {
                int sorteado = sortearPersonagem();
                Scanner ler = new Scanner(System.in);
                System.out.print("Digite a ação do(a) personagem " + getNaoSorteados().get(sorteado).getNome().intern() + " (Atacar/Defender/PowerUp): ");
                String acao = ler.next();
                if(acao.intern() == "Atacar" || acao.intern() == "atacar"){
                    getNaoSorteados().get(sorteado).atacar(getNaoSorteados().get(sorteado));
                }
                else{
                    executarAcao(acao, getNaoSorteados().get(sorteado));
                }
                naoSorteados.remove(sorteado);
                personagensComVida();
            }
            // depois daqui,irá iniciar um novo turno.
        }
        System.out.println("Personagem " + getPersonagensBatalhando().get(0).getNome() + " venceu essa batalha!");
        esvaziarVariaveis();
        //depois daqui,a batalha acaba.
    }

    //RECEBE COMO PARÂMETRO A AÇÃO QUE SERÁ EXECUTADA E O PERSONAGEM QUE IRÁ REALIZA-LÁ.
    private static void executarAcao(String acao, Personagem p) throws JogoException {
        if(acao.intern() == "Atacar" || acao.intern() == "atacar"){}
        else if(acao.intern() == "Defender" || acao.intern() == "defender"){p.defender();}
        else if(acao.intern() == "PowerUp" || acao.intern() == "Powerup" || acao.intern() == "powerUp" || acao.intern() == "powerup"){p.powerUp();}
        else{throw new JogoException("Ação inválida!");}
    }

    // VERIFICA SE DETERMINADO NOME É USADO POR ALGUM PERSONAGEM VIVO, CASO SEJA, RETORNA ESSE PERSONAGEM.
    public static Personagem validarNome(String nome) throws JogoException {
        for (int i = 0; i < getPersonagensBatalhando().toArray().length; i++) {
            if(nome.intern() == getPersonagensBatalhando().get(i).getNome().intern()){
                return getPersonagensBatalhando().get(i);
            }
        }
        throw new JogoException("Nome inválido!");
    }

    // verifica se os personagens estão com vida acima de 0 e,caso não esteja, remove ele das duas listas.
    private static void personagensComVida(){
        //Remover personagens mortos da lista dos que ainda estão batalhando, pois os mortos já perderam a batalha.
        for (int i = 0; i < getPersonagensBatalhando().toArray().length; i++) {
            if(getPersonagensBatalhando().get(i).getPontosDeVida() <= 0){
                System.out.println("Personagem " + getPersonagensBatalhando().get(i).getNome() + " morreu!");
                getPersonagensBatalhando().remove(i);
            }
        }

        //Remover personagens mortos da lista dos que ainda têm que ser sorteados,pois os mortos não têm que ser sorteados.
        for (int j = 0; j < naoSorteados.toArray().length; j++) {
            if(naoSorteados.get(j).getPontosDeVida() <= 0){
                naoSorteados.remove(j);
            }
        }
    }

    // RETORNA UM NÚMERO ALEATÓRIO NO INTEVALO DE ZERO ATÉ O TAMANHO DO ARRAY naoSorteados.
    private static int sortearPersonagem(){
        Random sorteado = new Random();
        int r = sorteado.nextInt(getNaoSorteados().toArray().length);

        return r;
    }

    //esvaziar as variaveis, para o caso de acontecer uma nova batalha.
    public static void esvaziarVariaveis(){
        personagensBatalhando.removeAll(personagensBatalhando);
        naoSorteados.removeAll(naoSorteados);
        setTurno(0);
    }

    private static void zerarPontosDeDefesa(){
        for (int i = 0; i < personagensBatalhando.toArray().length; i++) {
            personagensBatalhando.get(i).setPontosDeDefesa(0);
        }
    }

    private static void mensagemIniciarBatalha(){
        System.out.println("=========================================");
        System.out.println("         BATALHA INICIADA!");
        System.out.println("=========================================");
    }

    private static void mensagemNovoTurno(){
        System.out.println("=================TURNO " + getTurno() + "=================");
    }

    // VERIFICA SE JÁ SE PASSARAM 4 TURNOS. CASO SIM, CADA PERSONAGEM VIVO UTILIZA SUA HABILIDADE ESPECIAL.
    private static void usarHabilidadeEspecial(){
        if(getTurno()%4 == 0){ // a habilidade especial só é usada de 4 em 4 turnos.
            System.out.println("HABILIDADE ESPECIAL!");
            for (int i = 0; i < personagensBatalhando.toArray().length; i++) {
                personagensBatalhando.get(i).habilidadeEspecial();
            }
        }
    }

}
