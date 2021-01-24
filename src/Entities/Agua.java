package Entities;

import Aplicacao.Batalha;

public class Agua extends Personagem{

    public Agua(String tipo) {
        super(tipo);
    }

    public Agua(String nome, String tipo, Integer pontosDeVida, Integer ataqueMax, Integer pontosDeAtaque, Integer defesaMax, Integer pontosDeDefesa, Integer agilidade, String desvantagem) {
        super(nome, tipo, pontosDeVida, ataqueMax, pontosDeAtaque, defesaMax, pontosDeDefesa, agilidade, desvantagem);
    }

    @Override
    public void habilidadeEspecial() {
        setAtaqueMax(getAtaqueMax() + 1);
        setDefesaMax(getDefesaMax() + 1);
    }
}
