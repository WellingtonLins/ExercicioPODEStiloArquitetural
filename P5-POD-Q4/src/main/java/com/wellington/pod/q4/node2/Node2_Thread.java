package com.wellington.pod.q4.node2;

import com.wellington.pod.q4.entidade.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @Date 29/07/2017 @Time 08:45:22
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class Node2_Thread {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1234);
        try {
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                EntityManagerFactory emfPostgre = Persistence.createEntityManagerFactory("Postgre-PU");
                EntityManagerFactory emfMysql = Persistence.createEntityManagerFactory("Mysql-PU");
                List<User> userList = (List<User>) entrada.readObject();

                Salvando salvando = new Salvando("Postgre", emfPostgre, userList);
                Salvando salvando2 = new Salvando("Mysql", emfMysql, userList);
                
                salvando.start();
                salvando2.start();
            }
        } catch (Exception e) {

            System.out.println("Node2 erro " + e.getMessage());
        }
    }
}
