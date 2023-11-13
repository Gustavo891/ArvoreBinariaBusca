//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package classes;

public class ABB {
    No raiz = null;
    private int contador;
    private int posicaoEncontrada;

    public ABB() {
    }

    public No getRaiz() {
        return this.raiz;
    }

    public int posicao(int var1) {
        this.contador = 0;
        this.posicaoEncontrada = -1;
        this.buscaPosicaoRecursiva(this.raiz, var1);
        return this.posicaoEncontrada;
    }

    private void buscaPosicaoRecursiva(No var1, int var2) {
        if (var1 != null && this.posicaoEncontrada == -1) {
            this.buscaPosicaoRecursiva(var1.esquerda, var2);
            ++this.contador;
            if (var1.valor == var2) {
                this.posicaoEncontrada = this.contador;
            } else {
                this.buscaPosicaoRecursiva(var1.direita, var2);
            }
        }
    }

    public boolean contemRec(No var1, int var2) {
        if (var1 == null) {
            return false;
        } else if (var2 == var1.valor) {
            return true;
        } else {
            return var2 < var1.valor ? this.contemRec(var1.esquerda, var2) : this.contemRec(var1.direita, var2);
        }
    }

    public boolean remove(int var1) {
        if (!this.contemRec(this.raiz, var1)) {
            return false;
        } else {
            this.raiz = this.removeRec(this.raiz, var1);
            return true;
        }
    }

    private No removeRec(No var1, int var2) {
        if (var1 == null) {
            return var1;
        } else {
            if (var2 < var1.valor) {
                var1.esquerda = this.removeRec(var1.esquerda, var2);
                --var1.contEsquerda;
            } else if (var2 > var1.valor) {
                var1.direita = this.removeRec(var1.direita, var2);
                --var1.contDireita;
            } else {
                if (var1.esquerda == null) {
                    return var1.direita;
                }

                if (var1.direita == null) {
                    return var1.esquerda;
                }

                var1.valor = this.maiorValor(var1.esquerda);
                var1.esquerda = this.removeRec(var1.esquerda, var1.valor);
            }

            return var1;
        }
    }

    private int maiorValor(No var1) {
        int var2;
        for(var2 = var1.valor; var1.direita != null; var1 = var1.direita) {
            var2 = var1.direita.valor;
        }

        return var2;
    }

    public double media(int var1) {
        No var2 = this.buscaNo(this.raiz, var1);
        if (var2 == null) {
            return 0.0;
        } else {
            int var3 = this.somaDosValores(var2);
            int var4 = this.contaNos(var2);
            return var4 == 0 ? 0.0 : (double)var3 / (double)var4;
        }
    }

    private int somaDosValores(No var1) {
        return var1 == null ? 0 : var1.valor + this.somaDosValores(var1.esquerda) + this.somaDosValores(var1.direita);
    }

    private int contaNos(No var1) {
        return var1 == null ? 0 : 1 + this.contaNos(var1.esquerda) + this.contaNos(var1.direita);
    }

    private No buscaNo(No var1, int var2) {
        if (var1 == null) {
            return null;
        } else if (var2 == var1.valor) {
            return var1;
        } else {
            return var2 < var1.valor ? this.buscaNo(var1.esquerda, var2) : this.buscaNo(var1.direita, var2);
        }
    }

    public int mediana() {
        int var1 = this.raiz.contDireita + this.raiz.contEsquerda;
        return var1 % 2 == 0 ? this.enesimoElementoRecursivo(this.raiz, var1 / 2) : this.enesimoElementoRecursivo(this.raiz, (var1 + 1) / 2);
    }

    public int enesimoElementoRecursivo(No var1, int var2) {
        if (var1 == null) {
            return Integer.MIN_VALUE;
        } else {
            int var3 = (var1.esquerda != null ? var1.esquerda.contEsquerda + var1.esquerda.contDireita + 1 : 0) + 1;
            if (var3 == var2) {
                return var1.valor;
            } else {
                return var3 > var2 ? this.enesimoElementoRecursivo(var1.esquerda, var2) : this.enesimoElementoRecursivo(var1.direita, var2 - var3);
            }
        }
    }

    public boolean insere(int var1) {
        if (this.contemRec(this.raiz, var1)) {
            return false;
        } else {
            this.raiz = this.insereRec(this.raiz, var1);
            return true;
        }
    }

    private No insereRec(No var1, int var2) {
        if (var1 == null) {
            return new No(var2);
        } else {
            if (var2 < var1.valor) {
                var1.esquerda = this.insereRec(var1.esquerda, var2);
                ++var1.contEsquerda;
            } else if (var2 > var1.valor) {
                var1.direita = this.insereRec(var1.direita, var2);
                ++var1.contDireita;
            }

            return var1;
        }
    }

    public String pre_ordemRec(No var1) {
        if (var1 == null) {
            return "";
        } else {
            int var10000 = var1.valor;
            return "" + var10000 + " " + this.pre_ordemRec(var1.esquerda) + this.pre_ordemRec(var1.direita);
        }
    }

    public boolean ehCheiaRec(No var1) {
        if (var1 == null) {
            return true;
        } else if (var1.esquerda == null && var1.direita != null || var1.esquerda != null && var1.direita == null) {
            return false;
        } else {
            return this.ehCheiaRec(var1.esquerda) && this.ehCheiaRec(var1.direita);
        }
    }

    public boolean ehCompleta() {
        int var1 = this.altura(this.raiz);
        int var2 = this.contaNos(this.raiz);
        return (double)var2 >= Math.pow(2.0, (double)(var1 - 1)) && (double)var2 <= Math.pow(2.0, (double)var1) - 1.0;
    }

    public int altura(No var1) {
        return var1 == null ? 0 : 1 + Math.max(this.altura(var1.esquerda), this.altura(var1.direita));
    }

    public String imprimeArvore(int var1) {
        if (var1 == 1) {
            return this.formato1(this.raiz, 0);
        } else {
            return var1 == 2 ? this.formato2(this.raiz) : "Formato inválido";
        }
    }

    private String formato1(No var1, int var2) {
        if (var1 == null) {
            return "";
        } else {
            StringBuilder var3 = new StringBuilder();

            int var4;
            for(var4 = 0; var4 < var2 * 15; ++var4) {
                var3.append(" ");
            }

            var3.append(var1.valor);

            for(var4 = Integer.toString(var1.valor).length(); var4 < 15; ++var4) {
                var3.append("-");
            }

            var3.append("\n");
            var3.append(this.formato1(var1.esquerda, var2 + 1));
            var3.append(this.formato1(var1.direita, var2 + 1));
            return var3.toString();
        }
    }

    private String formato2(No var1) {
        if (var1 == null) {
            return "";
        } else {
            int var10000 = var1.valor;
            return "(" + var10000 + this.formato2(var1.esquerda) + this.formato2(var1.direita) + ")";
        }
    }
}
