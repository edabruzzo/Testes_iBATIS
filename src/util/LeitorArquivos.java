/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Emm
 */
public class LeitorArquivos implements Serializable {

    private static final long serialVersionUID = -1164222708545810708L;

    public String lerArquivoConsulta(String tipoConsulta) throws FileNotFoundException, IOException {

        String caminho = "C:\\Users\\Emm\\Documents\\NetBeansProjects\\Tests_JDBC\\ConsultasSQL\\";
        String nomeArquivo = null;
        String consultaSQL = "";

        switch (tipoConsulta) {

            case "ProcessoTRF2grau":

                nomeArquivo = "ProcessoTRF2grau.sql";

            case "PautaSessao":

                nomeArquivo = "PautaSessao.sql";

            case "Teste":

                nomeArquivo = "funcaoTeste.sql";

            case "criarBancoMySQL":

                nomeArquivo = "criaBancoMySQL.sql";

            default:
                System.out.println("Consulta inválida");
        }

        FileReader arquivo = new FileReader(caminho + nomeArquivo);
        BufferedReader buffRdr = new BufferedReader(arquivo);
        boolean eof = false;
        while (!eof) {
            String lineRead = buffRdr.readLine();
            if (lineRead == null) {
                eof = true;
            } else {

                consultaSQL += "\n" + lineRead;

            }
        }
        buffRdr.close();
        //SÓ USAREI SE CONTINUAR VINDO NULL NO INICÍO DA STRING      
//        consultaSQL = consultaSQL.substring(5);

        return consultaSQL;
    }

}
