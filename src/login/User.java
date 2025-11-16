package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    public Connection conectarBD(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1/usuario?user=root&password=123456";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) /*Esse catch não está tratando a exceção, só capturando*/ { }
        //pode retornar null caso o try retorne algum erro.
        return conn;
    }

    //Esses atributos deveriam ser declarados no início da classe
    public String nome = "";
    public boolean result = false;

    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        //pode ser nulo
        Connection conn = conectarBD();

        //instrução SQL
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql +=  " and senha = " + "'" + senha + "';";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                result = true;
                nome = rs.getString("nome"); }
        } catch (Exception e) /*Esse catch não está tratando a exceção, só capturando*/ { }
        //Deveria ter um finally aqui para fechar a conexão com o Banco de dados.
        return result;
    }
}