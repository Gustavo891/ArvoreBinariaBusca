
package controlador;

import visao.visualizar;

public class controle {
    public controle() {
    }

    public static void main(String[] var0) {
        visualizar var1 = new visualizar();
        System.out.println("Árvore Binária de Busca - Teste de Completude");
        var1.carregarArvore();
        var1.executarComandos();
    }
}
