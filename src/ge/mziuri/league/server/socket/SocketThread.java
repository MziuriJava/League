package ge.mziuri.league.server.socket;

import ge.mziuri.league.model.command.Command;
import ge.mziuri.league.model.command.CommandResult;
import ge.mziuri.league.model.exception.LeagueException;
import ge.mziuri.league.model.footballer.Footballer;
import ge.mziuri.league.server.dao.FootballerDAO;
import ge.mziuri.league.server.dao.FootballerDAOImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread implements Runnable {

    private final FootballerDAO footballerDAO = new FootballerDAOImpl();

    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Command command = (Command)in.readObject();
            switch (command) {
                case ADD_FOOTBALLER:
                    Footballer footballer = (Footballer)in.readObject();
                    try {
                        footballerDAO.addFootballer(footballer);
                        out.writeObject(CommandResult.SUCCESSFUL);
                    } catch (LeagueException ex) {
                        out.writeObject(CommandResult.FAILED);
                        ex.printStackTrace();
                    }
                    break;
                case GET_BOMBARDIER:
                    try {
                        Footballer bombardier = footballerDAO.getBombardier();
                        out.writeObject(CommandResult.SUCCESSFUL);
                        out.writeObject(bombardier);
                    } catch (LeagueException ex) {
                        out.writeObject(CommandResult.FAILED);
                    }
                    break;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
