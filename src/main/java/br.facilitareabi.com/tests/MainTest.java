package br.facilitareabi.com.tests;

import br.facilitareabi.com.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) {
        Connection conexao = ConnectionFactory.obterConexao();
        if (conexao != null) {
            System.out.println("Conexão OK!");
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Falha na conexão");
        }
    }
}
