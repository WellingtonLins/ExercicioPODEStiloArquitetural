package com.wellington.pod.q2.node2;

/**
 * @Date 28/07/2017 @Time 10:59:02
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import com.wellington.pod.q2.shared.Operacao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1235);

        try {
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                Operacao operacao = (Operacao) entrada.readObject();

                ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
                
                System.out.println("Operacao:\n" + operacao.getOperador());

                int numeroA = operacao.getNumero1();
                int numeroB = operacao.getNumero2();
                String operador = operacao.getOperador();

                switch (operador) {
                    case "*":
                         int resposta = 2 * numeroA * numeroB;
                        System.out.println("Recebi >=====>   " + numeroA + " " + operador + " " + numeroB);
                        System.out.println("Resposta : " + resposta);
                        operacao = new Operacao(operador, numeroA, numeroB, resposta);
                        saida.writeObject(operacao);
                        break;
                    case "/":

                        Socket socketNode3 = new Socket("127.0.0.1", 1236);
                        ObjectOutputStream outputToNode3 = new ObjectOutputStream(socketNode3.getOutputStream());
                        outputToNode3.writeObject(operacao);

                        ObjectInputStream inputFromNode3 = new ObjectInputStream(socketNode3.getInputStream());

                        operacao = (Operacao) inputFromNode3.readObject();
                        System.out.println("NODE 2 ==>  ==>  ==> : " + operacao.toString2());

                        saida.writeObject(operacao);
                        outputToNode3.writeObject(operacao);

                        break;
                    default:
                        System.out.println("Erro durante a elucidação da resposta!");
                        break;
                }

            }
        } catch (Exception e) {

            System.out.println("Node2 erro " + e.getMessage());
        }
    }
}
