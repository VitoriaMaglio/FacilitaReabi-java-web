package br.facilitareabi.com.tests;
import br.facilitareabi.com.dao.ConnectionFactory;
//TOMCAT NÃO RODA CLASSES JAVAS, APENAS servlets ou JSPs
public class TesteConexao {
     static void main(String[] args) {
        if(ConnectionFactory.obterConexao()==null){
            System.out.printf("erro");
        }else{
            System.out.println("Conexão realizada.");
        }
    }
}
