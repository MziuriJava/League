package ge.mziuri.league.dao;

import ge.mziuri.league.model.Footballer;
import ge.mziuri.league.model.Team;
import ge.mziuri.league.model.exception.LeagueException;
import ge.mziuri.league.util.DatabaseConnector;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FootballerDAOImpl implements FootballerDAO {

    @Override
    public void addFootballer(Footballer footballer) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO footballer (firstname, lastname, goals, fk_team) VALUES (?,?,?,?)");
            pstmt.setString(1, footballer.getFirstName());
            pstmt.setString(2, footballer.getLastName());
            pstmt.setInt(3, footballer.getGoals());
            pstmt.setInt(4, footballer.getTeam().getId());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            throw new LeagueException("Can't add footballer", ex);
        }
    }

    @Override
    public void increaseFootballerGoals(Footballer footballer) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE footballer SET goals = goals+1 WHERE id=?");
            pstmt.setInt(1, footballer.getId());
            pstmt.close();
            con.close();
            //swori mqonda mara ese uketesia ^.^ iiiiii
        } catch (Exception ex) {
            throw new LeagueException("Can't increase Footballer goals ", ex);
        }

    }


    @Override
    public void deleteFootballer(Footballer footballer) throws LeagueException {
        Connection con = null;
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE footballer WHERE id=?");
            pstmt.setInt(1, footballer.getId());
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            throw new LeagueException("Couldn't delete footballer try again later ", ex);
        }

    }

    @Override
    public List<Footballer> getFootballersByTeam(Team team) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstms = con.prepareStatement("SELECT * FROM footballer WHERE fk_team = ?");
            pstms.setInt(1, team.getId());
            ResultSet rs = pstms.executeQuery();
            List<Footballer> footballers = new ArrayList<>();
            while (rs.next()) {
                Footballer footballer = new Footballer();
                footballer.setId(rs.getInt("id"));
                footballer.setFirstName(rs.getString("firstname"));
                footballer.setLastName(rs.getString("lastname"));
                footballer.setGoals(rs.getInt("goals"));
                footballer.setTeam(team);
                footballers.add(footballer);
            }
            pstms.close();
            con.close();
            return footballers;
        } catch (Exception ex) {
            throw new LeagueException("Can't get footballer by team", ex);
        }
    }

    @Override
    public Footballer getBombardier() throws LeagueException {

        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstms = con.prepareStatement("SELECT * FROM footballer ORDER BY goals DESC LIMIT 1");
            ResultSet rs = pstms.executeQuery();
            Footballer footballer = new Footballer();
            footballer.setId(rs.getInt("id"));
            footballer.setFirstName(rs.getString("firstname"));
            footballer.setLastName(rs.getString("lastname"));
            footballer.setGoals(rs.getInt("goals"));
            pstms.close();
            con.close();
            return footballer;
//TEAM IDic vecadot rom wamovigot :)

        } catch (Exception ex) {
            throw new LeagueException("Couldn't get bombardier :( ", ex);
        }
    }

    @Override
    public void tradeFootballers(Footballer footballer1, Footballer footballer2) throws LeagueException {
        try {
            /*Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstms = con.prepareStatement("UPDATE footballer SET id=?, firstname=?, lastname=?, goals=? WHERE id=?");
            PreparedStatement pstms1 = con.prepareStatement("UPDATE footballer SET id=?, firstname=?, lastname=?, goals= WHERE id=?");

            pstms.setInt(1, footballer1.getId());
            pstms.setString(2, footballer1.getFirstName());
            pstms.setString(3, footballer1.getLastName());
            pstms.setInt(4, footballer1.getGoals());
            pstms.setInt(5, footballer2.getId());

            pstms1.setInt(1, footballer2.getId());
            pstms1.setString(2, footballer2.getFirstName());
            pstms1.setString(3, footballer2.getLastName());
            pstms1.setInt(4, footballer2.getGoals());
            pstms1.setInt(5, footballer1.getId());*/

            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstms = con.prepareStatement("UPDATE TABLE footballer SET fk_team=? WHERE id = ?");
            PreparedStatement pstms1 = con.prepareStatement("UPDATE TABLE footballer SET fk_team=? WHERE id = ?");
            pstms.setInt(1, footballer2.getTeam().getId());
            pstms.setInt(2, footballer1.getId());
            pstms1.setInt(1, footballer1.getTeam().getId());
            pstms1.setInt(2, footballer2.getId());

            //marto TEAMEBI UNDA GAGVECVALA -_-
            pstms.executeUpdate();
            pstms1.executeUpdate();
            pstms.close();
            pstms1.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
