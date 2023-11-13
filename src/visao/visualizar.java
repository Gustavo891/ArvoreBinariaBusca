//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package visao;

import classes.ABB;
import classes.No;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class visualizar {
    private ABB arvore = new ABB();

    public visualizar() {
    }

    public void carregarArvore() {
        try {
            BufferedReader var1 = new BufferedReader(new FileReader("src/arquivos/arquivo1.txt"));

            try {
                String var2 = var1.readLine();
                String[] var3 = var2.split(" ");
                String[] var4 = var3;
                int var5 = var3.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String var7 = var4[var6];
                    this.arvore.insere(Integer.parseInt(var7));
                }
            } catch (Throwable var9) {
                try {
                    var1.close();
                } catch (Throwable var8) {
                    var9.addSuppressed(var8);
                }

                throw var9;
            }

            var1.close();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }

    public void executarComandos() {
        try {
            BufferedReader var1 = new BufferedReader(new FileReader("src/arquivos/arquivo2.txt"));

            String var2;
            try {
                while((var2 = var1.readLine()) != null) {
                    this.processarComando(var2);
                }
            } catch (Throwable var5) {
                try {
                    var1.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            var1.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    private void processarComando(String var1) {
        No var2 = this.arvore.getRaiz();
        String[] var3 = var1.split(" ");
        switch (var3[0]) {
            case "IMPRIMA":
                System.out.println(this.arvore.imprimeArvore(Integer.parseInt(var3[1])));
                break;
            case "ALTURA":
                PrintStream var10000 = System.out;
                int var10001 = this.arvore.altura(var2);
                var10000.println("Altura: " + var10001);
                break;
            case "MEDIANA":
                System.out.println(this.arvore.mediana());
                break;
            case "ENESIMO":
                System.out.println(this.arvore.enesimoElementoRecursivo(var2, Integer.parseInt(var3[1])));
                break;
            case "MEDIA":
                System.out.println(this.arvore.media(Integer.parseInt(var3[1])));
                break;
            case "INSIRA":
                if (this.arvore.insere(Integer.parseInt(var3[1]))) {
                    System.out.println(Integer.parseInt(var3[1]) + " foi adicionado");
                } else {
                    System.out.println(Integer.parseInt(var3[1]) + " já está na árvore e não pode ser adicionado.");
                }
                break;
            case "BUSCAR":
                if (this.arvore.contemRec(var2, Integer.parseInt(var3[1]))) {
                    System.out.println("Chave foi encontrada.");
                } else {
                    System.out.println("Chave não foi encontrada.");
                }
                break;
            case "REMOVA":
                if (this.arvore.remove(Integer.parseInt(var3[1]))) {
                    System.out.println(Integer.parseInt(var3[1]) + " foi removido");
                } else {
                    System.out.println(Integer.parseInt(var3[1]) + " não está na árvore, não pode ser removido");
                }
                break;
            case "PREORDEM":
                System.out.println(this.arvore.pre_ordemRec(var2));
                break;
            case "CHEIA":
                if (this.arvore.ehCheiaRec(var2)) {
                    System.out.println("A arvore é cheia.");
                } else {
                    System.out.println("A arvore não é cheia.");
                }
                break;
            case "POSICAO":
                System.out.println(this.arvore.posicao(Integer.parseInt(var3[1])));
                break;
            case "COMPLETA":
                if (this.arvore.ehCompleta()) {
                    System.out.println("A arvore é completa.");
                } else {
                    System.out.println("A arvore não é completa.");
                }
        }

    }
}
