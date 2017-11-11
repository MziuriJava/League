package ge.mziuri.league.client;

import ge.mziuri.league.dao.FootballerDAO;
import ge.mziuri.league.dao.FootballerDAOImpl;
import ge.mziuri.league.dao.TeamDAO;
import ge.mziuri.league.dao.TeamDAOImpl;
import ge.mziuri.league.model.Footballer;
import ge.mziuri.league.model.Team;
import ge.mziuri.league.model.exception.LeagueException;

import java.util.List;

public class Runner {

    private static final FootballerDAO footballerDAO = new FootballerDAOImpl();
    private static final TeamDAO teamDAO = new TeamDAOImpl();

    public static void main(String[] args) {

    }

   private static void testAddFootballer() {
        Team team = new Team();
        team.setId(1);
        Footballer footballer = new Footballer();
        footballer.setFirstName("ვინმე");
        footballer.setLastName("ვინმეშვილი");
        footballer.setGoals(7);
        footballer.setTeam(team);
        try {
            footballerDAO.addFootballer(footballer);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }
    }

    private static void testIncreaseFootballerGoals() {

            Footballer footballer = new Footballer();
            footballer.setId(3);
        try {
            footballerDAO.increaseFootballerGoals(footballer);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }

    }

    private static void testDeleteFootballer(){
            Footballer footballer = new Footballer();
            footballer.setId(6);
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
            footballerDAO.getBombardier();
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() +" ,reason: "+ ex.getMessage());
        }

    }

    private static void testTradeFootballers() {
        Team team1 = new Team();
        team1.setId(7);
        Team team2 = new Team();
        team2.setId(10);
        Footballer footballer1 = new Footballer();
        Footballer footballer2 = new Footballer();
        footballer1.setId(32);
        footballer1.setTeam(team1);
        footballer2.setId(93);
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

    private static void testUpdateTeamRank(){
        Team team=new Team();
        team.setId(2);
        int newRank=3;
        try {
            teamDAO.updateTeamRank(team,newRank);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }
    }

    private static void testDeleteTeam(){
        Team team=new Team();
        team.setId(5);
        try {
            teamDAO.deleteTeam(team);
        } catch (LeagueException ex) {
            System.out.println(ex.getMessageKey() + " ,reason: " + ex.getMessage());
        }

    }

  /*  private static void testGetTeamsByRank(){
        T

    }?*/
}

