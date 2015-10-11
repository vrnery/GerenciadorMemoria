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

    public boolean getInicio(int tamanho){
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
    }

    public void String() {
        System.out.println("[Inicio: " + this.inicio + "\tTamanho: " + this.tamanho + "\tFim: " + this.fim + "]");
    }
}
