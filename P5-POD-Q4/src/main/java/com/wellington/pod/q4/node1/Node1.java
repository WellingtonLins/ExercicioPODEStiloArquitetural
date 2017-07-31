package com.wellington.pod.q4.node1;

/**
 * @Date 28/07/2017 @Time 10:59:02
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import com.wellington.pod.q4.entidade.User;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        Socket node = new Socket("127.0.0.1", 1234);

        List lista = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            lista.add(new User("User" + i));
        }

        ObjectOutputStream saida = new ObjectOutputStream(node.getOutputStream());
        saida.writeObject(lista);
        saida.close();
        node.close();

    }
}
