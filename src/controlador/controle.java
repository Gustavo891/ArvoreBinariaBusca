package controlador;

import visao.visualizar;

public class controle {
    public static void main(String[] args) {
        visualizar visao = new visualizar();

        System.out.println("Árvore Binária de Busca - Teste de Completude");
        visao.carregarArvore();
        visao.executarComandos();

    }
}
