/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadormemoria;

import java.util.ArrayList;

/**
 *
 * @author cstads
 */
public class GerenciadorMemoria {

    public static ArrayList<FreeMemory> free = new ArrayList<FreeMemory>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FreeMemory livre = new FreeMemory(0, 1024, 1023);
        free.add(0, livre);
        System.out.println("Teste");
        for(FreeMemory listar : free) {
            System.out.println("Inicio: " + listar.getInicio() + "\tTamanho: " + listar.getTamanho() + "\tFim: " + listar.getFim());
        }
        
        free.get(0).reduzirTamanho(150);
        
        for(FreeMemory listar : free) {
            System.out.println("Inicio: " + listar.getInicio() + "\tTamanho: " + listar.getTamanho() + "\tFim: " + listar.getFim());
        }
    }
    
}
