package guild;

import java.util.ArrayList;
import java.util.List;

public class Guild {
    private String name;
    private int capacity;
    private List<Player> roster;


    public Guild(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player){
        if(this.roster.size() < this.capacity){
            this.roster.add(player);
        }
    }

    public int count(){
        return this.roster.size();
    }

    public boolean removePlayer(String name){
        int index = findPlayerIndex(name);

        if(index != -1){
            roster.remove(index);
            return true;
        }

        return false;
    }

    public void promotePlayer(String name){
        int index = findPlayerIndex(name);

        if(index != -1) {
            roster.get(index).setRank("Member");
        }
    }

    public void demotePlayer(String name){
        int index = findPlayerIndex(name);

        if(index != -1) {
            roster.get(index).setRank("Trial");
        }
    }

    public int findPlayerIndex(String name){
        int index = -1;
        for (int i = 0; i < roster.size(); i++) {
            if(roster.get(i).getName().equals(name)){
                index = i;
            }
        }
        return index;
    }

    public Player[] kickPlayersByClass(String clazz){
        int count = 0;
        for (Player player : roster) {
            if(player.getClazz().equals(clazz)){
                count++;
            }
        }

        int index = 0;
        Player[] players = new Player[count];
        for (Player player : roster) {
            if(player.getClazz().equals(clazz)){
                players[index++] = player;
            }
        }

        for (Player player : players) {
            if(player.getClazz().equals(clazz)){
                roster.remove(player);
            }
        }

        return players;
    }

    public String report(){
        StringBuilder sb = new StringBuilder();

        sb.append("Players in the guild: " + name + ":");
        sb.append(System.lineSeparator());

        for (Player player : roster) {
            sb.append(player);
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
