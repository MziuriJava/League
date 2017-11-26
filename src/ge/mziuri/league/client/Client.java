package ge.mziuri.league.client;

import ge.mziuri.league.model.command.Command;
import ge.mziuri.league.model.command.CommandResult;
import ge.mziuri.league.model.footballer.Footballer;
import ge.mziuri.league.model.team.Team;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public void start() {
        try {
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.println(Messages.getMessage("chooseCommand"));
            Command commands[] = Command.values();
            for (int i = 0; i < commands.length; i++) {
                System.out.println(i + 1 + ". " + Messages.getMessage(commands[i].name()));
            }
            int num = scanner.nextInt();
            Command command = commands[num - 1];
            switch (command) {
                case ADD_FOOTBALLER:
                    scanner.nextLine();
                    System.out.println(Messages.getMessage("name"));
                    String name = scanner.nextLine();
                    System.out.println(Messages.getMessage("surname"));
                    String surname = scanner.nextLine();
                    System.out.println(Messages.getMessage("goals"));
                    int goals = scanner.nextInt();
                    System.out.println(Messages.getMessage("teamId"));
                    int teamId = scanner.nextInt();
                    Footballer footballer = new Footballer();
                    footballer.setFirstName(name);
                    footballer.setLastName(surname);
                    footballer.setGoals(goals);
                    Team team = new Team();
                    team.setId(teamId);
                    footballer.setTeam(team);
                    out.writeObject(command);
                    out.writeObject(footballer);
                    CommandResult result = (CommandResult)in.readObject();
                    System.out.println(Messages.getMessage(result.name()));
                    break;
                case GET_BOMBARDIER:
                    out.writeObject(command);
                    CommandResult bombardierResult = (CommandResult)in.readObject();
                    System.out.println(Messages.getMessage(bombardierResult.name()));
                    if (bombardierResult == CommandResult.SUCCESSFUL) {
                        Footballer bombardier = (Footballer)in.readObject();
                        System.out.println(bombardier);
                    }
                    break;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
