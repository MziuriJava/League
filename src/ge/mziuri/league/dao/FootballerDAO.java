package ge.mziuri.league.dao;

import ge.mziuri.league.model.Footballer;
import ge.mziuri.league.model.Team;
import ge.mziuri.league.model.exception.LeagueException;

import java.util.List;

public interface FootballerDAO {

    void addFootballer(Footballer footballer) throws LeagueException;

    void increaseFootballerGoals(Footballer footballer) throws LeagueException;

    void deleteFootballer(Footballer footballer) throws LeagueException;

    List<Footballer> getFootballersByTeam(Team team) throws LeagueException;

    Footballer getBombardier() throws LeagueException;

    void tradeFootballers(Footballer footballer1, Footballer footballer2) throws LeagueException;
}
