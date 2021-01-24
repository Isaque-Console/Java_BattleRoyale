package Entities;

import Aplicacao.Batalha;

public class Terra extends Personagem {

    public Terra(String tipo) {
        super(tipo);
    }

    public Terra(String nome, String tipo, Integer pontosDeVida, Integer ataqueMax, Integer pontosDeAtaque, Integer defesaMax, Integer pontosDeDefesa, Integer agilidade, String desvantagem) {
        super(nome, tipo, pontosDeVida, ataqueMax, pontosDeAtaque, defesaMax, pontosDeDefesa, agilidade, desvantagem);
    }

    @Override
    public void habilidadeEspecial() {
        setDefesaMax(getDefesaMax() + 2);
    }
}
