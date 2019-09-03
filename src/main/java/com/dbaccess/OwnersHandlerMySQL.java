package com.dbaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnersHandlerMySQL  implements OwnersHandler {

    private static final OwnersHandlerMySQL ownersHandler = new OwnersHandlerMySQL();

    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/phonebook?serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String USER_PASSWORD = "admin77";

    private OwnersHandlerMySQL(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver was loaded successful");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, USER_PASSWORD);
            return conn;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized OwnersHandlerMySQL getInstance(){
        return ownersHandler;
    }

    @Override
    public int addOwner(Owner owner) throws SQLException {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO Owners (name, phoneNumber) VALUES (?,?)")){
            statement.setString(1, owner.getName());
            statement.setString(2, owner.getPhoneNumber());
            statement.executeUpdate();
            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM Owners");
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public List<Owner> getAllOwners() throws SQLException {
        List<Owner> owners = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT name,  phoneNumber FROM Owners ORDER BY name")){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                owners.add(new Owner(rs.getString(1),rs.getString(2) ));
            }
        }
        return owners;
    }
}
