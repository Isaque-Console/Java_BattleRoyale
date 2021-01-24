package Entities;

import Aplicacao.Batalha;
import Exception.JogoException;

import java.util.Scanner;

import static Aplicacao.Batalha.validarNome;

public abstract class Personagem {
    private String nome;
    private String tipo;
    private Integer pontosDeVida = 1;
    private Integer ataqueMax = 0; // NÍVEL DO ATAQUE DO PERSONAGEM.
    private Integer defesaMax = 0; // NÍVEL DA DEFESA DO PERSONAGEM.
    private Integer pontosDeDefesa = 0; // NÍVEL DA DEFESA QUE ESTÁ SENDO UTILIZADA NO MOMENTO.
    private Integer agilidade = 0;
    private String desvantagem;

    public Personagem(String tipo){
        this.tipo = tipo;
    }

    public Personagem(String nome, String tipo, Integer pontosDeVida, Integer ataqueMax, Integer defesaMax, Integer pontosDeDefesa, Integer agilidade, String desvantagem) {
        this.nome = nome;
        this.tipo = tipo;
        this.pontosDeVida = pontosDeVida;
        this.ataqueMax = ataqueMax;
        this.defesaMax = defesaMax;
        this.pontosDeDefesa = pontosDeDefesa;
        this.agilidade = agilidade;
        this.desvantagem = desvantagem;
    }

    // A HABILIDADE ESPECIAL É IMPLEMENTADA EM CADA TIPO, POIS ELA MUDA DE ACORDO COM O TIPO PERSONAGEM.
    public abstract void habilidadeEspecial();

    // MUDA O VALOR DA VARIÁVEL DE VIDA, DE ACORDO COM O DANO SOFRIDO.
    public void atacar(Personagem atacante) throws JogoException {
        System.out.print("Digite o nome do personagem que irá sofrer o ataque: ");
        Scanner ler = new Scanner(System.in);
        String nome = ler.next();
        if(nome.intern() == atacante.getNome().intern()){
            throw new JogoException("Você não pode si atacar!");
        }
        Personagem atacado = validarNome(nome);

        int dano = danoNoAtaque(atacante.getAtaqueMax(),atacado.getPontosDeDefesa(),atacado);
        Integer vidaAtualizada = atacado.getPontosDeVida() - dano;
        atacado.setPontosDeVida(vidaAtualizada);
        atacado.setPontosDeDefesa(0); // ZERA OS PONTOS DE DEFESA DO PERSONAGEM ATACADO, POIS O PERSONAGEM SÓ PODE SE DEFENDER UM VEZ POR TURNO.
        System.out.println(dano + " ponto(s) de dano no(a) personagem " + atacado.getNome().intern() + "!");
    }

    //CALCULA O DANO QUE SERÁ DESCONTADO NA VIDA DO PERSONAGEM ATACADO.
    public int danoNoAtaque(int ataque, int defesa,Personagem atacado){
        if(atacado.getDesvantagem().intern() == getTipo().intern()){
            ataque = ataque + 1;
        }
        int dano = ataque - defesa;
        if(atacado.getAgilidade() > getAgilidade()){
            dano = (int)(Math.floor(dano/2));
            System.out.println("Personagem atacado desviou do golpe crítico, e sofrerá dano parcial!");
        }
        if(dano > 0){
            return dano;
        }
        return 0;
    }

    //HABILITA OS PONTOS DE DEFESA DO PERSONAGEM
    public void defender(){
        setPontosDeDefesa(getDefesaMax());
    }

    //PERSONAGEM USA O POWERUP, QUE ACRESCENTA 1 PONTO NO ATAQUE DELE.
    public void powerUp(){
        setAtaqueMax(getAtaqueMax() + 1);
        System.out.println("Acrescentado 1 ponto no ataque!");
    }

    // metodos para buscar e modificar os atributos(getter e setter)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPontosDeVida() {
        return pontosDeVida;
    }

    public void setPontosDeVida(Integer pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public Integer getAtaqueMax() {
        return ataqueMax;
    }

    public void setAtaqueMax(Integer ataqueMax) {
        this.ataqueMax = ataqueMax;
    }

    public Integer getDefesaMax() {
        return defesaMax;
    }

    public void setDefesaMax(Integer defesaMax) {
        this.defesaMax = defesaMax;
    }

    public Integer getPontosDeDefesa() {
        return pontosDeDefesa;
    }

    public void setPontosDeDefesa(Integer pontosDeDefesa) {
        this.pontosDeDefesa = pontosDeDefesa;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public String getDesvantagem() {
        return desvantagem;
    }

    public void setDesvantagem(String desvantagem) {
        this.desvantagem = desvantagem;
    }
}
