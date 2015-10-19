/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadormemoria;

/**
 *
 * @author cstads
 */
public class WorstFit {
    public void procurar(String nome, int tamanho) {
        boolean contem = false;
        TableMemory.ordena(GerenciadorMemoria.free);
        TableMemory.ajustarTabela();
        TableMemory.ordenaWorst(GerenciadorMemoria.free);

        if (tamanho > 0) {
            for (Memoria memoria : GerenciadorMemoria.memoria) {
                if (memoria.getNome().equals(nome)) {
                    contem = true;
                }
            }
            if (!contem) {
                inserir(nome, tamanho);
            }
        } else {
            for (Memoria memoria : GerenciadorMemoria.memoria) {
                if (memoria.getNome().equals(nome)) {
                    excluir(nome, memoria.getTamanho());
                    break;
                }
            }
        }
    }

    public void inserir(String nome, int tamanho) {
        int ini = 0, tam = 0, fim = 0;
        for (TableMemory tabela : GerenciadorMemoria.free) {
            if (tamanho <= tabela.getTamanho()) {
                System.out.println("\nCarregando na memoria o processo " + nome + " na possição " + tabela.getInicio() + " com tamanho " + tamanho);
                Memoria addMemoria = new Memoria(tabela.getInicio(), nome, tamanho);
                GerenciadorMemoria.memoria.add(addMemoria);
                tabela.reduzirTamanho(tamanho);
                ini = tabela.getInicio();
                tam = tabela.getTamanho();
                fim = tabela.getFim();
                GerenciadorMemoria.free.remove(tabela);
                
                if (tabela.getTamanho() != 0) {
                    GerenciadorMemoria.free.add(new TableMemory(ini, tam, fim));
                }
                break;
            } else {
                System.out.println("\nMemoria cheia");
            }
        }
    }

    public void excluir(String nome, int tamanho) {
        int ini, tam, fim;
        boolean conjuncao = false;
        for (Memoria memoria : GerenciadorMemoria.memoria) {
            if (memoria.getNome().equals(nome)) {
                System.out.println("\nExcluido da memoria o processo " + nome + " na possição " + memoria.getInicio());
                for (TableMemory liberar : GerenciadorMemoria.free) {
                    if (!conjuncao) {
                        if ((memoria.getInicio() + memoria.getTamanho()) == liberar.getInicio()) {
                            GerenciadorMemoria.free.remove(liberar);
                            liberar.decrementarInicio(memoria.getTamanho());
                            GerenciadorMemoria.free.add(liberar);
                            conjuncao = true;
                            break;
                        } else if (memoria.getInicio() - 1 == liberar.getFim()) {
                            GerenciadorMemoria.free.remove(liberar);
                            liberar.incrementarFim(memoria.getTamanho());
                            GerenciadorMemoria.free.add(liberar);
                            conjuncao = true;
                            break;
                        }
                    }
                }
                if (!conjuncao) {
                    GerenciadorMemoria.free.add(new TableMemory(memoria.getInicio(), memoria.getTamanho(), (memoria.getInicio() + memoria.getTamanho() - 1)));
                }
                GerenciadorMemoria.memoria.remove(memoria);
                break;
            }
        }
        TableMemory.ajustarTabela();
    }
}
