package com.wellington.pod.q2.shared;

import java.io.Serializable;

/**
 * @Date 28/07/2017  @Time 10:59:02
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class Operacao  implements Serializable{
      private String operador;
      private int numero1;
      private int numero2;
      private int resposta;

    public Operacao() {
    }

    public Operacao(String operador, int numero1, int numero2) {
        this.operador = operador;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public Operacao(String operador, int numero1, int numero2, int resposta) {
        this.operador = operador;
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.resposta = resposta;
    }

    public String getOperador() {
        return operador;
    }

    public int getNumero1() {
        return numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public int getResposta() {
        return resposta;
    }

 @Override
    public String toString() {
        return "2 "+ operador + " " +numero1 + " " + operador + " " + numero2 + " = " + resposta;
    }

    public String toString2() {
        return "2 * " + numero1 + " / " + numero2 + " = " + resposta;
    }
     
      
}
