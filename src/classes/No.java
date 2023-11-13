package classes;

public class No {
    int valor;
    No esquerda;
    No direita;
    int contEsquerda = 0;  // Guarda o número de nós na subárvore esquerda
    int contDireita = 0;   // Guarda o número de nós na subárvore direita

    public No(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
}
