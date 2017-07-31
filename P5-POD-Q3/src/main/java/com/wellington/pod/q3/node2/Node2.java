package com.wellington.pod.q3.node2;

/**
 * @Date 28/07/2017 @Time 14:58
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import com.wellington.pod.q3.shared.Operacao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(1235);

            while (true) {
                Socket socket = server.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                Operacao operacao = (Operacao) entrada.readObject();

                System.out.println("Operacao:\n" + operacao.getOperador());

                int numeroA = operacao.getNumero1();
                int numeroB = operacao.getNumero2();
                String operador = operacao.getOperador();

                switch (operador) {
                    case "-":
                        int resposta = numeroA - numeroB;
                        System.out.println("Recebi >=====>   " + numeroA + " " + operador + " " + numeroB);
                        operacao = new Operacao(operador, numeroA, numeroB, resposta);

                        Socket socketNode4 = new Socket("127.0.0.1", 1237);

                        ObjectOutputStream saida = new ObjectOutputStream(socketNode4.getOutputStream());
                        saida.writeObject(operacao);

                        break;
                    case "+":
                     
                        Socket socketNode3 = new Socket("127.0.0.1", 1236);

                        ObjectOutputStream saidaNode3 = new ObjectOutputStream(socketNode3.getOutputStream());
                        saidaNode3.writeObject(operacao);

                        break;

               
                    default:
                        System.out.println("Erro durante a elucidação da resposta!");
                        break;
                }
            }
    }
}
