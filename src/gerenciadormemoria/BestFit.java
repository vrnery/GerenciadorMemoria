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
public class BestFit {

    public void procurar(String nome, int tamanho) {
        boolean contem = false;
        TableMemory.ordena(GerenciadorMemoria.free);
        TableMemory.ajustarTabela();

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
            if (tam <= tabela.getTamanho()) {
                ini = tabela.getInicio();
                tam = tabela.getTamanho();
                fim = tabela.getFim();
            }
        }
        System.out.println(ini + " " + tam + " " + fim);
        if (tam >= tamanho) {
            System.out.println("\nCarregando na memoria o processo " + nome + " na possição " + ini + " com tamanho " + tamanho);
            Memoria addMemoria = new Memoria(ini, nome, tamanho);
            TableMemory tabela = new TableMemory(ini, tam, fim);
            GerenciadorMemoria.free.remove(tabela);
            GerenciadorMemoria.memoria.add(addMemoria);
            tabela.reduzirTamanho(tamanho);
            //GerenciadorMemoria.free.add(tabela);
        } else {
            System.out.println("\nMemoria cheia");
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
