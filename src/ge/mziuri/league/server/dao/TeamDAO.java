package ge.mziuri.league.server.dao;

import ge.mziuri.league.model.team.SortDirection;
import ge.mziuri.league.model.team.Team;
import ge.mziuri.league.model.exception.LeagueException;

import java.util.List;

public interface TeamDAO {

    void addTeam(Team team) throws LeagueException;

    void updateTeamRank(Team team, int newRank) throws LeagueException;

    void deleteTeam(Team team) throws LeagueException;

    List<Team> getTeamsSortedByRank(SortDirection direction) throws LeagueException;
}
