package Entities;

import Aplicacao.Batalha;

public class Fogo extends Personagem {

    public Fogo(String tipo){
        super(tipo);
    }

    public Fogo(String nome, String tipo, Integer pontosDeVida, Integer ataqueMax, Integer defesaMax, Integer pontosDeDefesa, Integer agilidade, String desvantagem) {
        super(nome, tipo, pontosDeVida, ataqueMax, defesaMax, pontosDeDefesa, agilidade, desvantagem);
    }

    // A HABILIDADE ESPECIAL DO FOGO É ACRESCENTAR 2 PONTOS NO SEU ATAQUE.
    @Override
    public void habilidadeEspecial() {
        setAtaqueMax(getAtaqueMax() + 2);
    }
}
