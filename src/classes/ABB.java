package classes;

public class ABB {
    No raiz;

    public ABB() {
        raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    private int contador;
    private int posicaoEncontrada;

    public int posicao(int valor) {
        contador = 0;
        posicaoEncontrada = -1;
        buscaPosicaoRecursiva(raiz, valor);
        return posicaoEncontrada;
    }

    private void buscaPosicaoRecursiva(No atual, int valor) {
        if (atual == null || posicaoEncontrada != -1) {
            return;
        }

        buscaPosicaoRecursiva(atual.esquerda, valor);

        contador++;
        if (atual.valor == valor) {
            posicaoEncontrada = contador;
            return;
        }
        buscaPosicaoRecursiva(atual.direita, valor);
    }

    public boolean contemRec(No atual, int valor) {
        if (atual == null) {
            return false;
        }
        if (valor == atual.valor) {
            return true;
        }
        return valor < atual.valor ? contemRec(atual.esquerda, valor) : contemRec(atual.direita, valor);
    }

    public boolean remove(int valor) {
        if (!contemRec(raiz, valor)) {
            return false;
        }
        raiz = removeRec(raiz, valor);
        return true;
    }


    private No removeRec(No atual, int valor) {
        if (atual == null)
            return atual;

        if (valor < atual.valor) {
            atual.esquerda = removeRec(atual.esquerda, valor);
            atual.contEsquerda--;
        } else if (valor > atual.valor) {
            atual.direita = removeRec(atual.direita, valor);
            atual.contDireita--;
        } else {
            if (atual.esquerda == null)
                return atual.direita;
            else if (atual.direita == null)
                return atual.esquerda;

            atual.valor = maiorValor(atual.esquerda);
            atual.esquerda = removeRec(atual.esquerda, atual.valor);
        }
        return atual;
    }

    private int maiorValor(No raiz) {
        int maiorValor = raiz.valor;
        while (raiz.direita != null) {
            maiorValor = raiz.direita.valor;
            raiz = raiz.direita;
        }
        return maiorValor;
    }

    public double media(int x) {
        No nodeX = buscaNo(raiz, x);
        if (nodeX == null) {
            return 0;
        }

        int soma = somaDosValores(nodeX);
        int quantidade = contaNos(nodeX);

        if (quantidade == 0) {
            return 0;
        }
        return (double) soma / quantidade;
    }

    private int somaDosValores(No no) {
        if (no == null) {
            return 0;
        }
        return no.valor + somaDosValores(no.esquerda) + somaDosValores(no.direita);
    }

    private int contaNos(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + contaNos(no.esquerda) + contaNos(no.direita);
    }

    private No buscaNo(No atual, int valor) {
        if (atual == null) {
            return null;
        }
        if (valor == atual.valor) {
            return atual;
        }
        if (valor < atual.valor) {
            return buscaNo(atual.esquerda, valor);
        } else {
            return buscaNo(atual.direita, valor);
        }
    }

    public int mediana() {
        int total = raiz.contDireita + raiz.contEsquerda;
        if (total % 2 == 0) {
            return enesimoElementoRecursivo(raiz, total / 2);
        } else {
            return enesimoElementoRecursivo(raiz, (total + 1) / 2);
        }
    }

    public int enesimoElementoRecursivo(No atual, int n) {
        if (atual == null) {
            return Integer.MIN_VALUE;
        }

        int posAtual = (atual.esquerda != null ? atual.esquerda.contEsquerda + atual.esquerda.contDireita + 1 : 0) + 1;

        if (posAtual == n) {
            return atual.valor;
        } else if (posAtual > n) {
            return enesimoElementoRecursivo(atual.esquerda, n);
        } else {
            return enesimoElementoRecursivo(atual.direita, n - posAtual);
        }
    }

    public boolean insere(int valor) {
        if (contemRec(raiz, valor)) {
            return false;
        }
        raiz = insereRec(raiz, valor);
        return true;
    }

    private No insereRec(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = insereRec(no.esquerda, valor);
            no.contEsquerda++;
        } else if (valor > no.valor) {
            no.direita = insereRec(no.direita, valor);
            no.contDireita++;
        }

        return no;
    }

    public String pre_ordemRec(No no) {
        if (no == null) {
            return "";
        }

        return no.valor + " " + pre_ordemRec(no.esquerda) + pre_ordemRec(no.direita);
    }

    public boolean ehCheiaRec(No no) {
        if (no == null) {
            return true;
        }

        if ((no.esquerda == null && no.direita != null) || (no.esquerda != null && no.direita == null)) {
            return false;
        }

        return ehCheiaRec(no.esquerda) && ehCheiaRec(no.direita);
    }

    public boolean ehCompleta() {
        int alturaTotal = altura(raiz);
        int n = contaNos(raiz);
        return n >= Math.pow(2, alturaTotal - 1) && n <= Math.pow(2, alturaTotal) - 1;
    }

    public int altura(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    public String imprimeArvore(int s) {
        if (s == 1) {
            return formato1(raiz, 0);
        } else if (s == 2) {
            return formato2(raiz);
        }
        return "Formato inválido";
    }

    private String formato1(No atual, int profundidade) {
        if (atual == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < profundidade * 15; i++) {
            sb.append(" ");
        }

        sb.append(atual.valor);

        for (int i = Integer.toString(atual.valor).length(); i < 15; i++) {
            sb.append("-");
        }

        sb.append("\n");

        sb.append(formato1(atual.esquerda, profundidade + 1));
        sb.append(formato1(atual.direita, profundidade + 1));

        return sb.toString();
    }

    private String formato2(No atual) {
        if (atual == null) {
            return "";
        }
        return "(" + atual.valor + formato2(atual.esquerda) + formato2(atual.direita) + ")";
    }
}
