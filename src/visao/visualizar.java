package visao;

import classes.ABB;
import classes.No;

import java.io.*;

public class visualizar {

    private ABB arvore;

    public visualizar() {
        this.arvore = new ABB();
    }

    public void carregarArvore() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/arquivos/arquivo1.txt"))) {
            String linha = br.readLine();
            String[] valores = linha.split(" ");
            for (String valor : valores) {
                arvore.insere(Integer.parseInt(valor));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executarComandos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/arquivos/arquivo2.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                processarComando(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processarComando(String comando) {

        No raiz = arvore.getRaiz();

        String[] partes = comando.split(" ");
        switch (partes[0]) {
            case "IMPRIMA":
                System.out.println(arvore.imprimeArvore(Integer.parseInt(partes[1])));
                break;
            case "ALTURA":
                System.out.println("Altura: " + arvore.altura(raiz));
                break;
            case "MEDIANA":
                System.out.println(arvore.mediana());
                break;
            case "ENESIMO":
                System.out.println(arvore.enesimoElementoRecursivo(raiz, Integer.parseInt(partes[1])));
                break;
            case "MEDIA":
                System.out.println(arvore.media(Integer.parseInt(partes[1])));
                break;
            case "INSIRA":
                if (arvore.insere(Integer.parseInt(partes[1]))) {
                    System.out.println(Integer.parseInt(partes[1]) + " foi adicionado");
                } else {
                    System.out.println(Integer.parseInt(partes[1]) + " já está na árvore e não pode ser adicionado.");
                }
                break;
            case "BUSCAR":
                if (arvore.contemRec(raiz, Integer.parseInt(partes[1]))) {
                    System.out.println("Chave foi encontrada.");
                } else {
                    System.out.println("Chave não foi encontrada.");
                }
                break;
            case "REMOVA":
                if (arvore.remove(Integer.parseInt(partes[1]))) {
                    System.out.println(Integer.parseInt(partes[1]) + " foi removido");
                } else {
                    System.out.println(Integer.parseInt(partes[1]) + " não está na árvore, não pode ser removido");
                }
                break;
            case "PREORDEM":
                System.out.println(arvore.pre_ordemRec(raiz));
                break;
            case "CHEIA":
                if (arvore.ehCheiaRec(raiz)) {
                    System.out.println("A arvore é cheia.");
                } else {
                    System.out.println("A arvore não é cheia.");
                }
                break;
            case "POSICAO":
                System.out.println(arvore.posicao(Integer.parseInt(partes[1])));
                break;
            case "COMPLETA":
                if (arvore.ehCompleta()) {
                    System.out.println("A arvore é completa.");
                } else {
                    System.out.println("A arvore não é completa.");
                }
                break;
        }
    }

}
