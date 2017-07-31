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
public class Node2 {

    static long inicioPostgre;
    static long fimPostgre;
    static long inicio;
    static long fim;
    static int xPostgre = 0;
    static int x = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1234);
        try {
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                List<User> userList = (List<User>) entrada.readObject();

                EntityManagerFactory emfPostgre = Persistence.createEntityManagerFactory("Postgre-PU");
                EntityManager em1 = emfPostgre.createEntityManager();
                
                inicioPostgre = System.currentTimeMillis();
                
                for (User user : userList) {
                    try {
                        em1.getTransaction().begin();

                        em1.persist(user);

                        em1.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        em1.getTransaction().rollback();
                    }
                }
                
                fimPostgre = System.currentTimeMillis();
                xPostgre = (int) (fimPostgre - inicioPostgre);
                System.out.println("\nTEMPO DECORRIDO PARA O POSTGRE " + xPostgre + "\n");

                em1.close();
                emfPostgre.close();

                EntityManagerFactory emfMysql = Persistence.createEntityManagerFactory("Mysql-PU");
                EntityManager em2 = emfMysql.createEntityManager();
                
                inicio = System.currentTimeMillis();
                for (User user : userList) {
                    try {
                        em2.getTransaction().begin();

                        em2.persist(user);

                        em2.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        em2.getTransaction().rollback();
                    }
                }
                fim = System.currentTimeMillis();
                x = (int) (fim - inicio);
                System.out.println("\nTEMPO DECORRIDO PARA O MySQL " + x + "\n");

                int total =  x + xPostgre ;
                System.out.println("\nTEMPO DECORRIDO TOTAL PARA AMBOS " + total + "\n");

                
                
                em2.close();
                emfMysql.close();
            }
        } catch (Exception e) {

            System.out.println("Node2 erro " + e.getMessage());
        }
    }
}
