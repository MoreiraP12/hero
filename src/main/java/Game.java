import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private final TerminalScreen screen;
    private Arena arena;


    public Game(int w,int h) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(w,h)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        arena = new Arena(w,h);

    }
    private void Draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        while(true) {
            Draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character &&
                    key.getCharacter() == 'q')
                screen.close();

            else if(key.getKeyType() == KeyType.EOF )
                break;

            else if(processKey(key)){
                screen.close();
            }
        }
    }

    private boolean processKey(KeyStroke key) {
        return arena.processKey(key);
    }

}
