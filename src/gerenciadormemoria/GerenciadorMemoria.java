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

    public static String processo[][] = {{"SO", "100"}, {"MSN", "50"}, {"FIREFOX", "80"}, {"WORD", "100"}, {"EXCEL", "120"}, {"PHOTOSHOP", "450"}, {"WORD", "0"}, {"WORD", "100"}, {"EXCEL", "0"}, {"MSN", "0"}, {"PHOTOSHOP", "0"}, {"ACROBAT", "350"}, {"COUNTER", "230"}, {"MSN", "50"}, {"COUNTER", "0"}, {"WIRESHARK", "150"}, {"TERMINAL", "20"}, {"CHROME", "100"}, {"WORD", "0"}, {"DROPBOX", "50"}, {"TERMINAL", "0"}, {"WIRESHARK", "0"}, {"WORD", "100"}};
    public static ArrayList<TableMemory> free = new ArrayList<TableMemory>();
    public static ArrayList<Memoria> memoria = new ArrayList<Memoria>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TableMemory livre = new TableMemory(0, 1024, 1023);
        free.add(0, livre);
        
        FirstFit first = new FirstFit();
        System.out.println("================ Espaço livre na memoria ================");
        free.get(0).String();
        System.out.println("=========================================================");
        for(String processoAtual[] : processo) {
            first.procurar(processoAtual[0], Integer.parseInt(processoAtual[1]));
            System.out.println("================ Espaço livre na memoria ================");
            //System.out.println(free.toString2());
            for(TableMemory tabela : free) {
                tabela.String();
            };
            System.out.println("=================== Memoria utilizada ===================");
            //System.out.println(memoria.toString());
            for(Memoria carregada : memoria) {
                carregada.String();
            }
            System.out.println("=========================================================");
        }
    }
    
}
