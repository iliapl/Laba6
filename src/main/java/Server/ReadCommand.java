package Server;

import forCommands.CommandProcessing.ExecuteCommands;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.processing.Generated;
import java.util.NoSuchElementException;
@AllArgsConstructor
public class ReadCommand {
    private final ExecuteCommands commands;
    @Getter
    private boolean needExit = false;
    public void Read(){
                System.out.println("Введите комманду");
                try {
                    inputLine = scanner.nextLine();
                }catch(NoSuchElementException e){
                    System.out.println("Произошла ошибка при чтении команды: " + e.getMessage());
                    return;
                }
                needExit = commands.executeCommand(inputLine);
    }
}
