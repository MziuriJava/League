package ge.mziuri.league.server.dao;

import ge.mziuri.league.server.dao.FootballerDAO;
import ge.mziuri.league.server.dao.FootballerDAOImpl;
import ge.mziuri.league.server.dao.TeamDAO;
import ge.mziuri.league.server.dao.TeamDAOImpl;
import ge.mziuri.league.model.Footballer;
import ge.mziuri.league.model.Team;
import ge.mziuri.league.model.exception.LeagueException;

import java.util.List;

public class DAOTest {

    private static final FootballerDAO footballerDAO = new FootballerDAOImpl();
    private static final TeamDAO teamDAO = new TeamDAOImpl();

    public static void main(String[] args) {
//        testAddFootballer();
//        testIncreaseFootballerGoals();
//        testDeleteFootballer();
//        testGetFootballersByTeam();
//        testGetBombardier();
//        testTradeFootballers();
//        testAddTeam();
//        testUpdateTeamRank();
        testDeleteTeam();
    }

    private static void testAddFootballer() {
        Team team = new Team();
        team.setId(2);
        Footballer footballer = new Footballer();
        footballer.setFirstName("რამე");
        footballer.setLastName("რამეძე");
        footballer.setGoals(10);
        footballer.setTeam(team);
        try {
            footballerDAO.addFootballer(footballer);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }
    }

    private static void testIncreaseFootballerGoals() {
        Footballer footballer = new Footballer();
        footballer.setId(4);
        try {
            footballerDAO.increaseFootballerGoals(footballer);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }

    }

    private static void testDeleteFootballer() {
        Footballer footballer = new Footballer();
        footballer.setId(5);
        try {
            footballerDAO.deleteFootballer(footballer);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }

    }

    private static void testGetFootballersByTeam() {
        Team team = new Team();
        team.setId(1);
        try {
            List<Footballer> footballers = footballerDAO.getFootballersByTeam(team);
            for (Footballer footballer : footballers) {
                System.out.println(footballer);
            }
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }
    }

    private static void testGetBombardier() {
        try {
            System.out.println(footballerDAO.getBombardier());
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }

    }

    private static void testTradeFootballers() {
        Team team1 = new Team();
        team1.setId(1);
        Team team2 = new Team();
        team2.setId(2);
        Footballer footballer1 = new Footballer();
        Footballer footballer2 = new Footballer();
        footballer1.setId(4);
        footballer1.setTeam(team1);
        footballer2.setId(7);
        footballer2.setTeam(team2);
        try {
            footballerDAO.tradeFootballers(footballer1, footballer2);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason " + ex.getMessage());
        }
    }


    private static void testAddTeam() {
        Team team = new Team();
        team.setName("Otoebi");
        team.setRank(3);
        try {
            teamDAO.addTeam(team);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }

    }

    private static void testUpdateTeamRank() {
        Team team = new Team();
        team.setId(2);
        int newRank = 3;
        try {
            teamDAO.updateTeamRank(team, newRank);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }
    }

    private static void testDeleteTeam() {
        Team team = new Team();
        team.setId(2);
        try {
            teamDAO.deleteTeam(team);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }
    }

    private static void testGetTeamsByRank(){

    }
}

