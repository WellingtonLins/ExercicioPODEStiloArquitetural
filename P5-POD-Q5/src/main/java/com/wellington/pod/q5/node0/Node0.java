package com.wellington.pod.q5.node0;

import com.wellington.pod.q5.entidade.Repositorio;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Date 29/07/2017  @Time 17:54:27
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class Node0 {
  
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Socket node = new Socket("127.0.0.1", 1234);//NODE_1
        Socket node = new Socket("127.0.0.1", 1235);//NODE_2

        Repositorio operacao = new Repositorio("-", 10, 2);
//        Repositorio operacao = new Repositorio("+", 10, 2);

        ObjectOutputStream saida = new ObjectOutputStream(node.getOutputStream());
        saida.writeObject(operacao);

        ObjectInputStream entrada = new ObjectInputStream(node.getInputStream());

        operacao = (Repositorio) entrada.readObject();
        System.out.println("Resposta da operacao :  ==> " + operacao.toString());

        saida.close();

    }

}