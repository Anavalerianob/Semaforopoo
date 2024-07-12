💻Código de uma aplicação de um semáforo 🚦 feito em Java,na disciplina de POO(Programação Orientada a Objetos)

![Captura_de_tela_2024-07-12_184213-removebg-preview](https://github.com/user-attachments/assets/de705cf6-13ee-49ee-b206-43599c7f8a42)

📝O projeto segue os seguintes requisitos:

 ❗As janelas devem ser construídas usando sempre a classe JFrame.
 ❗Os modelos geométricos necessários devem ser construídos usando a classe Graphics. O
método para tal ação deverá ser o “paint” que existe em várias classes Java.
 ❗Todos os fluxos existentes nos jogos deverão ser construídos com uso da classe Thread ou
por implementação da interface Runnable.
 ❗ Tenha sempre bem claro na construção do programa os conceitos da Orientação a Objetos,
identificando com comentários sempre que necessário.
Regras de negócio da aplicação:
 ❗ Classe: Semáforo - Atributos: aberto, fechado, atenção (que pode ser usado um único
atributo), tipo (digital ou tradicional), tempo_aberto, tempo_fechado. Metodos: void abre(), void fecha(),
void tempo(int t), boolean estaAberto(), estaFechado()
 ❗ Para testar, crie um “semaforo”, abra e feche o mesmo, mude o tipo de “digital” para
“tradicional”, altere os tempos e use o metodo estaAberto para verificar se o semáforo está aberto. De
forma incremental, traduza o seguinte conjunto de classes em um programa Java.
 ❗ Classe: Cruzamento - Atributos: qtd_semaforos, semaforos(). Metodo: void sincroniza(), int
totalDeSemaforos.
 ❗ Para testar, crie um Cruzamento e insira no mínimo dois semáforos e use a Interface
(Figura 1) para realizar os testes. A interface deverá ter os meios de interação (botões e
similares) com usuário para abrir, fechar, mudar tipo, inserir semáforos e o que for necessário.
