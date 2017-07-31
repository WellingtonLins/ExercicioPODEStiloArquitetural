package com.wellington.pod.q5.node2;

import com.wellington.pod.q5.entidade.Repositorio;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Date 29/07/2017 @Time 17:54:27
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class Node2 {

    private static final String localPath = System.getProperty("user.dir");
    private static final String caminho = localPath + "\\diff.txt";

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1235);

        try {
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                Repositorio repositorio = (Repositorio) entrada.readObject();

                ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

                System.out.println("Operacao:\n" + repositorio.getOperador());

                int numeroA = repositorio.getNumero1();
                int numeroB = repositorio.getNumero2();
                String operador = repositorio.getOperador();

                switch (operador) {
                    case "-":
                        int resposta = numeroA - numeroB;

                        System.out.println("Recebi >=====>   " + numeroA + " " + operador + " " + numeroB);
                        System.out.println("Resposta : " + resposta);
                        repositorio = new Repositorio(operador, numeroA, numeroB, resposta);
                        saida.writeObject(repositorio);
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append(repositorio.toString());
                            sb.append("\r\n");
                            String dado = new String(sb);
                            Files.write(Paths.get(caminho), dado.getBytes(), StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            System.out.println("Node1 Erro ao salvar os dados da operação! veja se o arquivo existe!");

                        }
                       
                        break;
                    default:
                        System.out.println("Node2 Erro nos dados da operação!");
                        break;
                }

            }
        } catch (Exception e) {

            System.out.println("Node2 erro " + e.getMessage());
        }
    }

}
