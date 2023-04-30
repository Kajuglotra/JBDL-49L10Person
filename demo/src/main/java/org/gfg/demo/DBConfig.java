package org.gfg.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConfig {

    @Bean
    public Connection connection(){
        Connection connection = null;
        try{
            connection= DriverManager.getConnection("jdbc:oracle:thin:@10.56.110.151:1545/testdb1","AMCARE_SIT", "amcare_sit");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
