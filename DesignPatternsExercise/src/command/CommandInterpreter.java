package command;

import builder.MapBuilder;
import builder.MyMapBuilder;
import stone.ThrowFast;
import stone.ThrowSlow;

import java.util.HashMap;
import java.util.Map;

public class CommandInterpreter implements CommandListener{
    private Map<String, Command> commands;
            /*= Map.of(
            "W", new MoveUp(),
            "S", new MoveDown(),
            "A", new MoveLeft(),
            "D", new MoveRight(),
            "AS", new MoveFirstDiagonal(),
            "DS", new MoveSecondDiagonal(),
            "F", new Fire()
    );*/

    public CommandInterpreter(){
        MapBuilder<String, Command> builder = new MyMapBuilder<>();
        this.commands = builder
                .entry("W", new MoveUp())
                .entry("S", new MoveDown())
                .entry("A", new MoveLeft())
                .entry("D", new MoveRight())
                .entry("AS", new MoveFirstDiagonal())
                .entry("DS", new MoveSecondDiagonal())
                .entry("FS", new Fire(new ThrowFast()))
                .entry("F", new Fire(new ThrowSlow()))
                .build();
    }
    @Override
    public void handleCommand(String type) {
        Command command = commands.get(type);
        if(command == null){
            throw new IllegalArgumentException("Invalid command type");
        }
        command.execute();
        /*switch(type.toUpperCase()){
            case "A":
                break;
            case "D":
                break;
            case "W":
                break;
            case "S":
                break;
            case"F":
                break;
            default:
                throw new IllegalArgumentException("Invalid command type");
        }*/
    }
}
