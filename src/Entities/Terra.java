package Entities;

import Aplicacao.Batalha;

public class Terra extends Personagem {

    public Terra(String tipo) {
        super(tipo);
    }

    public Terra(String nome, String tipo, Integer pontosDeVida, Integer ataqueMax, Integer defesaMax, Integer pontosDeDefesa, Integer agilidade, String desvantagem) {
        super(nome, tipo, pontosDeVida, ataqueMax, defesaMax, pontosDeDefesa, agilidade, desvantagem);
    }

    // A HABILIDADE ESPECIAL DO TERRA É ACRESCENTAR 2 PONTOS NA SUA DEFESA.
    @Override
    public void habilidadeEspecial() {
        setDefesaMax(getDefesaMax() + 2);
    }
}
