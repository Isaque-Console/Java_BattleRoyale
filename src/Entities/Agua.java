package Entities;

import Aplicacao.Batalha;

public class Agua extends Personagem{

    public Agua(String tipo) {
        super(tipo);
    }

    public Agua(String nome, String tipo, Integer pontosDeVida, Integer ataqueMax, Integer defesaMax, Integer pontosDeDefesa, Integer agilidade, String desvantagem) {
        super(nome, tipo, pontosDeVida, ataqueMax, defesaMax, pontosDeDefesa, agilidade, desvantagem);
    }

    // A HABILIDADE ESPECIAL DO AGUA É ACRESCENTAR 1 PONTO NO ATAQUE E 1 PONTO NA DEFESA.
    @Override
    public void habilidadeEspecial() {
        setAtaqueMax(getAtaqueMax() + 1);
        setDefesaMax(getDefesaMax() + 1);
    }
}
