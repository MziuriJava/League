package ge.mziuri.league.server.dao;

import ge.mziuri.league.model.footballer.Footballer;
import ge.mziuri.league.model.team.Team;
import ge.mziuri.league.model.exception.LeagueException;
import ge.mziuri.league.server.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FootballerDAOImpl implements FootballerDAO {

    @Override
    public void addFootballer(Footballer footballer) throws LeagueException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DatabaseConnector.getConnection();
            pstmt = con.prepareStatement("INSERT INTO footballer (firstname, lastname, goals, fk_team) VALUES (?,?,?,?)");
            pstmt.setString(1, footballer.getFirstName());
            pstmt.setString(2, footballer.getLastName());
            pstmt.setInt(3, footballer.getGoals());
            pstmt.setInt(4, footballer.getTeam().getId());
            pstmt.executeUpdate();
        } catch (Exception ex) {
            throw new LeagueException("Can't add footballer", ex);
        } finally {
            try {
                pstmt.close();
                con.close();
            } catch (Exception ignored) {}
        }
    }

    @Override
    public void increaseFootballerGoals(Footballer footballer) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE footballer SET goals = goals + 1 WHERE id=?");
            pstmt.setInt(1, footballer.getId());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            throw new LeagueException("Can't increase Footballer goals ", ex);
        }
    }


    @Override
    public void deleteFootballer(Footballer footballer) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM footballer WHERE id=?");
            pstmt.setInt(1, footballer.getId());
            pstmt.executeUpdate();
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
            rs.next();
            Footballer footballer = new Footballer();
            footballer.setId(rs.getInt("id"));
            footballer.setFirstName(rs.getString("firstname"));
            footballer.setLastName(rs.getString("lastname"));
            footballer.setGoals(rs.getInt("goals"));
            pstms.close();
            con.close();
            return footballer;
        } catch (Exception ex) {
            throw new LeagueException("Couldn't get bombardier :( ", ex);
        }
    }

    @Override
    public void tradeFootballers(Footballer footballer1, Footballer footballer2) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstms = con.prepareStatement("UPDATE footballer SET fk_team=? WHERE id = ?");
            PreparedStatement pstms1 = con.prepareStatement("UPDATE footballer SET fk_team=? WHERE id = ?");
            pstms.setInt(1, footballer2.getTeam().getId());
            pstms.setInt(2, footballer1.getId());
            pstms1.setInt(1, footballer1.getTeam().getId());
            pstms1.setInt(2, footballer2.getId());
            pstms.executeUpdate();
            pstms1.executeUpdate();
            pstms.close();
            pstms1.close();
            con.close();
        } catch (Exception e) {
            throw new LeagueException("Couldn't trade :( ", e);
        }
    }
}
