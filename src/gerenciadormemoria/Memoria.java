/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadormemoria;

import java.util.Arrays;

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

    public static void ordenarMemoria() {
        int cont = GerenciadorMemoria.memoria.size();
        Memoria[] memoria = new Memoria[cont];
        Memoria aux;

        for (int i = 0; i < GerenciadorMemoria.memoria.size(); i++) {
            memoria[i] = GerenciadorMemoria.memoria.get(i);
        }

        for (int i = 0; i < cont; i++) {
            for (int j = cont - 1; j > i; j--) {
                if (memoria[i].getInicio() > memoria[j].getInicio()) {
                    aux = memoria[i];
                    memoria[i] = memoria[j];
                    memoria[j] = aux;
                }
            }
        }
        GerenciadorMemoria.memoria.clear();
        GerenciadorMemoria.memoria.addAll(Arrays.asList(memoria));
    }

    public void String() {
        System.out.println("[Inicio: " + this.inicio + "\tNome: " + this.nome + "\tTamanho: " + this.tamanho + "\tFim: " + (this.inicio + this.tamanho - 1) + "]");
    }
}
