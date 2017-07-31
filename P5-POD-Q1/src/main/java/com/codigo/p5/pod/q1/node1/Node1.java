package com.codigo.p5.pod.q1.node1;

/**
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Node1 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 1234);
        OutputStream out = socket.getOutputStream();
      
        String numeroA = String.valueOf(2);
        String numeroB = String.valueOf(3);

        String envio = numeroA + " " + numeroB + " \n";
        out.write(envio.getBytes());

        InputStream in = socket.getInputStream();
        byte[] bytes = new byte[100];
        in.read(bytes);
     
        System.out.println(new String(bytes).trim());
    }

}
