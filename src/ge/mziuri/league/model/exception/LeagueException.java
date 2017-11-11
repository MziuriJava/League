package ge.mziuri.league.model.exception;

public class LeagueException extends Exception {

    private String messageKey;

    private Exception ex;

    public LeagueException(String messageKey) {
        this.messageKey = messageKey;
    }

    public LeagueException(String messageKey, Exception ex) {
        super(ex);
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
