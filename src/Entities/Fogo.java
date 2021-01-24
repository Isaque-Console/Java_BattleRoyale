package Entities;

import Aplicacao.Batalha;

public class Fogo extends Personagem {

    public Fogo(String tipo){
        super(tipo);
    }

    public Fogo(String nome, String tipo, Integer pontosDeVida, Integer ataqueMax, Integer pontosDeAtaque, Integer defesaMax, Integer pontosDeDefesa, Integer agilidade, String desvantagem) {
        super(nome, tipo, pontosDeVida, ataqueMax, pontosDeAtaque, defesaMax, pontosDeDefesa, agilidade, desvantagem);
    }

    @Override
    public void habilidadeEspecial() {
        setAtaqueMax(getAtaqueMax() + 2);
    }
}
