package com.wellington.pod.q2.node3;

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

public class Node3 {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1236);

        try {
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                Operacao operacao = (Operacao) entrada.readObject();

                ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

                System.out.print("Operacao:  [[  " + operacao.getOperador() + "  ]]");

                int numeroA = operacao.getNumero1();
                int numeroB = operacao.getNumero2();
                String operador = operacao.getOperador();
                switch (operador) {
                    case "/":
                        int resposta = (2 * numeroA )/ numeroB;
                        System.out.println("   ==> Recebi >=====>   " + numeroA + " " + operador + " " + numeroB);
                        System.out.println("Resposta : " + resposta);

                        operacao = new Operacao(operador, numeroA, numeroB, resposta);
                        saida.writeObject(operacao);

                        break;
                    case "*":
                        try {
                            System.out.print(",vou encaminhar para o NODE1\n");

                            Socket socketNode1 = new Socket("127.0.0.1", 1234);
                            ObjectOutputStream outputToNode1 = new ObjectOutputStream(socketNode1.getOutputStream());
                            outputToNode1.writeObject(operacao);

                            ObjectInputStream inputFromNode1 = new ObjectInputStream(socketNode1.getInputStream());

                            operacao = (Operacao) inputFromNode1.readObject();

                            saida.writeObject(operacao);
                            outputToNode1.writeObject(operacao);
                        } catch (Exception e) {
                            System.out.print(",vou encaminhar para o NODE2\n");

                            Socket socketNode1 = new Socket("127.0.0.1", 1235);
                            ObjectOutputStream outputToNode1 = new ObjectOutputStream(socketNode1.getOutputStream());
                            outputToNode1.writeObject(operacao);

                            ObjectInputStream inputFromNode1 = new ObjectInputStream(socketNode1.getInputStream());

                            operacao = (Operacao) inputFromNode1.readObject();

                            saida.writeObject(operacao);
                            outputToNode1.writeObject(operacao);
                        }

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
