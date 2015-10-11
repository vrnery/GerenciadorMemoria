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
public class Memoria {
    private int inicio;
    private String nome;
    private int tamanho;

    public Memoria(int inicio, String nome, int tamanho) {
        this.inicio = inicio;
        this.nome = nome;
        this.tamanho = tamanho;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void String() {
        System.out.println("[Inicio: " + this.inicio + "\tNome: " + this.nome + "\tTamanho: " + this.tamanho + "\tFim: " + (this.inicio + this.tamanho - 1) + "]");
    }
}
