package com.codigo.p5.pod.q1.node2;

/**
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1234);
        try {
            while (true) {
                Socket socket = server.accept();

                InputStream inputStream = socket.getInputStream();

                byte[] bytes = new byte[1024];
                inputStream.read(bytes);

                String entrada = new String(bytes);
                String[] numeros = entrada.split(" ");

                Integer numeroA = Integer.valueOf(numeros[0]);
                Integer numeroB = Integer.valueOf(numeros[1]);

                System.out.println("Recebi >=====>   " + numeroA + "  " + numeroB);

                if (numeroA != numeroB) {
                    Socket envio = new Socket("127.0.0.1", 12345);
                    OutputStream out = envio.getOutputStream();
                    out.write(entrada.getBytes());

                    inputStream = envio.getInputStream();
                    byte[] b = new byte[100];
                    inputStream.read(b);
                    String calculo = new String(b).trim();
                    System.out.println(calculo);
                    OutputStream  outputStream = socket.getOutputStream();
                    outputStream.write(calculo.getBytes());

                } else {
                    System.out.println("Sou igual");
                   OutputStream  outputStream = socket.getOutputStream();
                    outputStream.write("0".getBytes());
                }

            }
        } catch (Exception e) {

            System.out.println("Node2 erro " + e.getMessage());
        }
    }
}
