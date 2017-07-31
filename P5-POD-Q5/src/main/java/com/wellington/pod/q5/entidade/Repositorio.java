package com.wellington.pod.q5.entidade;

import java.io.Serializable;

/**
 * @Date 29/07/2017  @Time 18:06:51
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class Repositorio  implements Serializable{

      private String operador;
      private int numero1;
      private int numero2;
      private int resposta;

  
    public Repositorio(String operador, int numero1, int numero2) {
        this.operador = operador;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public Repositorio(String operador, int numero1, int numero2, int resposta) {
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
        return  numero1 + " " + operador +  " " + numero2 + " = " + resposta ;
    }
     
}