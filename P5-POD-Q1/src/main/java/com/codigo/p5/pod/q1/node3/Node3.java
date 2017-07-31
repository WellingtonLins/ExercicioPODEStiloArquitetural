package com.codigo.p5.pod.q1.node3;

/**
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node3 {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(12345);

        while (true) {
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);

            String entrada = new String(bytes);
            String numeros[] = entrada.split(" ");

            Integer numeroA = Integer.valueOf(numeros[0]);
            Integer numeroB = Integer.valueOf(numeros[1]);
            
//            Math.pow(base, expoente);
             
               

            
            String resposta = String.valueOf(Math.pow(numeroA, numeroA) + Math.pow(numeroB, numeroB));
            
            OutputStream out = socket.getOutputStream();
            out.write(resposta.getBytes());

            System.out.println(resposta);

        }

    }
}
