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
public class FirstFit {
    public void procurar(String nome, int tamanho) {
        boolean contem = false;
        if (tamanho > 0) {
            for(Memoria memoria : GerenciadorMemoria.memoria) {
                if(memoria.getNome().equals(nome)) {
                    contem = true;
                }
            }
            if(!contem) {
                inserir(nome, tamanho);
            }
        } else {
            excluir(nome, tamanho);
        }
    }
    
    public void inserir(String nome, int tamanho) {
        boolean cheia = true;
        for(TableMemory tabela : GerenciadorMemoria.free) {
            if(tabela.getInicio(tamanho)) {
                System.out.println("\nCarregando na memoria o processo " + nome + " na possição " + tabela.getInicio() + " com tamanho " + tamanho);
                Memoria addMemoria = new Memoria(tabela.getInicio(), nome, tamanho);
                GerenciadorMemoria.memoria.add(addMemoria);
                tabela.reduzirTamanho(tamanho);
                cheia = false;
                break;
            }
        }
        if(cheia) {
            System.out.println("\nMemoria cheia");
        }
    }
    
    public void excluir(String nome, int tamanho) {
        for(Memoria memoria : GerenciadorMemoria.memoria) {
            if(memoria.getNome().equals(nome)) {
                System.out.println("\nExcluido da memoria o processo " + nome + " na possição " + memoria.getInicio());
                for(int i=0; i<GerenciadorMemoria.free.size(); i++) {
                    System.out.println("inicio: " + memoria.getInicio() + " tamanho: " + memoria.getTamanho());
                    if(memoria.getInicio() + memoria.getTamanho() == GerenciadorMemoria.free.get(i).getInicio()) {
                        int a = GerenciadorMemoria.free.get(i).getInicio();
                        int b = GerenciadorMemoria.free.get(i).getTamanho();
                        int c = GerenciadorMemoria.free.get(i).getFim();
                        TableMemory libera = new TableMemory((a-100),b,c);
                        libera.decrementarInicio((int)tamanho);
                        System.out.println("inicio: " + libera.getInicio() + " tamanho: " + libera.getTamanho() + " fim: " + libera.getFim());
                        GerenciadorMemoria.free.set(i, libera);
                        break;
                    } //else if (memoria.getInicio() - 1 == GerenciadorMemoria.free.get(i).getFim()) {
//                        GerenciadorMemoria.free.get(i).incrementarFim(tamanho);
//                        break;
//                    }
                }
                GerenciadorMemoria.memoria.remove(memoria);
                break;
            }
        }
    }
}
