package com.wellington.pod.q3.node1;

/**
 * @Date 28/07/2017 @Time 14:58
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import com.wellington.pod.q3.shared.Operacao;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket node = new Socket("127.0.0.1", 1235);//NODE_2 diff
//        Socket node = new Socket("127.0.0.1", 1236);//NODE_3  sum

    
        Operacao operacao = new Operacao("-", 10, 2);
//        Operacao operacao = new Operacao("-", 10, 2);

        ObjectOutputStream saida = new ObjectOutputStream(node.getOutputStream());
        saida.writeObject(operacao);
        saida.close();
        node.close();
       
    }
}
