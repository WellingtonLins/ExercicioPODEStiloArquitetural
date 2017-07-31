package com.wellington.pod.q2.cliente;

/**
 * @Date 28/07/2017 @Time 10:59:02
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import com.wellington.pod.q2.shared.Operacao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket node = new Socket("127.0.0.1", 1234);//NODE_1   2*x*y
//      Socket node = new Socket("127.0.0.1", 1235);//NODE_2   2*x*y
//      Socket node = new Socket("127.0.0.1", 1236);//NODE_3     2x/y

        Operacao operacao = new Operacao("*", 3, 2);
//        Operacao operacao = new Operacao("/", 3,2);

        ObjectOutputStream saida = new ObjectOutputStream(node.getOutputStream());
        saida.writeObject(operacao);

        ObjectInputStream entrada = new ObjectInputStream(node.getInputStream());

        operacao = (Operacao) entrada.readObject();

        System.out.println("Resposta da operacao :  ==> " + operacao.getResposta());

        saida.close();

    }

}
