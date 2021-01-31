# Java Battle Royale
## descrição:
Jogo com menu inicial, com opções de:

&bull;Sair do jogo: Fecha a aplicação.

&bull;Criar personagem: Escolhe o tipo, o nome e distribue 10 pontos entre os atributos do personagem criado.

&bull;Deletar personagem: Personagem é deletado do arquivo.

&bull;Editar: Escolhe um novo nome para algum personagem.

&bull;Exibir personagens: Exibe todos os personagens criados.

&bull;Batalhar: Cada personagem terá de escolher uma ação(atacar,defender,power up) por turno. A ordem de quem irá executar a ação primeiro será sorteada. No ataque, deverá ser escolhido o personagem alvo e, caso o alvo tenha desvantagem em relação ao atacante, é adicionado mais 1 ponto no dano do ataque. No power up, será acrescentado 1 ponto no nível de ataque do personagem. Caso o personagem não escolha se defender, sua defesa será zero. A agilidade do personagem serve para ele desviar do dano crítico, assim, se o personagem alvo tiver agilidade maior que o personagem que o atacou, aquele irá receber apenas metade do dano(sendo um valor inteiro, arredondado para baixo).Cada tipo de personagem tem uma habilidade especial específica, que será usada de forma automatica a cada 4 turnos. No fogo a habilidade especial é acrescentar 2 pontos no nível do ataque, na água acrescenta 1 ponto no nível da defesa e 1 ponto no nível do ataque, e na terra acrescenta 2 pontos no nível da defesa. A batalha acaba quando resta apenas um jogador com vida.

OBS: Neste programa é usado um arquivo de texto para gravar as informações dos personagens, simulando um banco de dados.

## Linguagem utilizada:
Java
