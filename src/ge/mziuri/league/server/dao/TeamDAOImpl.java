package ge.mziuri.league.server.dao;

import ge.mziuri.league.model.SortDirection;
import ge.mziuri.league.model.Team;
import ge.mziuri.league.model.exception.LeagueException;
import ge.mziuri.league.server.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamDAOImpl implements TeamDAO {

    @Override
    public void addTeam(Team team) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO team (name, rank) VALUES (?,?)");
            pstmt.setString(1, team.getName());
            pstmt.setInt(2, team.getRank());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            throw new LeagueException("Couldn't add team " + ex);
        }
    }

    @Override
    public void updateTeamRank(Team team, int newRank) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE team SET rank=? WHERE id=?");
            pstmt.setInt(1, newRank);
            pstmt.setInt(2, team.getId());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            throw new LeagueException("Couldn't update team rank " + ex);
        }
    }

    @Override
    public void deleteTeam(Team team) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM team WHERE id=?");
            pstmt.setInt(1, team.getId());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            throw new LeagueException("Couldn't delete team " + ex);
        }
    }

    @Override
    public List<Team> getTeamsSortedByRank(SortDirection direction) throws LeagueException {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM TEAM ORDER BY rank " + direction.name());
            ResultSet rs = pstmt.executeQuery();
            List<Team> teams = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int rank = rs.getInt("rank");
                Team team = new Team();
                team.setId(id);
                team.setName(name);
                team.setRank(rank);
            }
            pstmt.close();
            con.close();
            return teams;
        } catch (Exception ex) {
            throw new LeagueException("Can't sort teams by rank " + ex);
        }
    }
}

