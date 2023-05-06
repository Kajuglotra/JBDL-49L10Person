package org.gfg.JBDL49L10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Configuration
public class DBConfig {

    @Bean //  is to with methods
    public Connection connection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdl_49", "root" ,"rootroot");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }
}
