package hu.alkfejl.dao;

import hu.alkfejl.config.EredmenyConfiguration;
import hu.alkfejl.model.Eredmeny;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EredmenyDAOinpl implements EredmenyDAO{

    private static final String SELECT_10_EREDMENY = "SELECT * FROM Eredmeny ORDER BY score DESC LIMIT 10";
    private static final String INSERT_EREDMENY = "INSERT INTO Eredmeny(name, score, date , time ) VALUES (?, ?, ?, ?)";
    private static final String FIND_FIRST = "SELECT score FROM Eredmeny ORDER BY score DESC LIMIT 1";;
    private String connectionURL;

    public EredmenyDAOinpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionURL = EredmenyConfiguration.getValue("db.url");
    }

    @Override
    public List<Eredmeny> findALl() {
        List<Eredmeny> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_10_EREDMENY);
        ){
            while (rs.next()){
                Eredmeny eredmeny = new Eredmeny();
                eredmeny.setId(rs.getInt("id"));
                eredmeny.setName(rs.getString("name"));
                Date date = Date.valueOf(rs.getString("date"));
                eredmeny.setDate(date.toLocalDate());
                eredmeny.setScore(rs.getInt("score"));
                eredmeny.setTime(rs.getString("time"));

                result.add(eredmeny);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean save(Eredmeny eredmeny) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(INSERT_EREDMENY);
        ){
            stmt.setString(1, eredmeny.getName());
            stmt.setInt(2, eredmeny.getScore());
            stmt.setString(3, eredmeny.getDate().toString());
            stmt.setString(4, eredmeny.getTime());

            int erintettSorok = stmt.executeUpdate();
            if (erintettSorok != 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public int findFirst() {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(FIND_FIRST);
        ){
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("score");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}