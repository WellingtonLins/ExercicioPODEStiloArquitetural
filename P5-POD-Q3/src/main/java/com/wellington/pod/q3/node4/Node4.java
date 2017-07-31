package com.wellington.pod.q3.node4;

/**
 * @Date 28/07/2017 @Time 14:58
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import com.wellington.pod.q3.shared.Operacao;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node4 {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1237);
            while (true) {
            Socket socket = server.accept();

                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                Operacao operacao = (Operacao) entrada.readObject();

                System.out.println("Operacao:\n" + operacao.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
