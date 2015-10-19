/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadormemoria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cstads
 */
public class TableMemory {

    private int inicio;
    private int tamanho;
    private int fim;

    TableMemory(int inicio, int tamanho, int fim) {
        this.inicio = inicio;
        this.tamanho = tamanho;
        this.fim = fim;
    }

    public int getInicio() {
        return inicio;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getFim() {
        return fim;
    }

    public boolean getInicio(int tamanho) {
        if (this.tamanho >= tamanho) {
            return true;
        } else {
            return false;
        }
    }

    public void reduzirTamanho(int tamanho) {
        this.inicio += tamanho;
        this.tamanho = this.fim - this.inicio + 1;
    }

    public void decrementarInicio(int tamanho) {
        this.inicio -= tamanho;
        this.tamanho = this.fim - this.inicio + 1;
    }

    public void incrementarFim(int tamanho) {
        this.fim += tamanho;
        this.tamanho = this.fim - this.inicio + 1;
    }

    public static void ordena(ArrayList<TableMemory> tm) {
        int cont = tm.size();
        TableMemory[] ord = new TableMemory[cont];
        TableMemory aux;

        for (int i = 0; i < cont; i++) {
            ord[i] = tm.get(i);
        }
        for (int i = 0; i < cont; i++) {
            for (int j = cont - 1; j > i; j--) {
                if (ord[i].getInicio() > ord[j].getInicio()) {
                    aux = ord[i];
                    ord[i] = ord[j];
                    ord[j] = aux;
                }
            }
        }
        GerenciadorMemoria.free.clear();
        GerenciadorMemoria.free.addAll(Arrays.asList(ord));
    }

    public static void ordenaBest(ArrayList<TableMemory> tm) {
        int cont = tm.size();
        TableMemory[] ord = new TableMemory[cont];
        TableMemory aux;

        for (int i = 0; i < cont; i++) {
            ord[i] = tm.get(i);
        }
        for (int i = 0; i < cont; i++) {
            for (int j = cont - 1; j > i; j--) {
                if (ord[i].getTamanho()< ord[j].getTamanho()) {
                    aux = ord[i];
                    ord[i] = ord[j];
                    ord[j] = aux;
                }
            }
        }
        GerenciadorMemoria.free.clear();
        GerenciadorMemoria.free.addAll(Arrays.asList(ord));
    }

    public static void ajustarTabela() {
        ArrayList<TableMemory> ajustar = new ArrayList<TableMemory>();
        TableMemory comparar = null;
        if (GerenciadorMemoria.free.size() > 1) {
            for (int i = 0; i < GerenciadorMemoria.free.size(); i++) {
                if (i < GerenciadorMemoria.free.size() - 1) {
                    if ((GerenciadorMemoria.free.get(i).getFim() + 1) == GerenciadorMemoria.free.get(i + 1).getInicio()) {
                        comparar = GerenciadorMemoria.free.get(i);
                        comparar.incrementarFim(GerenciadorMemoria.free.get(i + 1).getTamanho());
                        GerenciadorMemoria.free.remove(i + 1);
                    } else {
                        comparar = GerenciadorMemoria.free.get(i);
                    }
                } else if (i == GerenciadorMemoria.free.size() - 1) {
                    comparar = GerenciadorMemoria.free.get(i);
                }
                ajustar.add(comparar);
            }
            GerenciadorMemoria.free.clear();
            GerenciadorMemoria.free.addAll(ajustar);
        }
    }

    public void String() {
        System.out.println("[Inicio: " + this.inicio + "\tTamanho: " + this.tamanho + "\tFim: " + this.fim + "]");
    }
}
