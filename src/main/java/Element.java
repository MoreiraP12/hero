import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    private Position position;
    public Element(int x,int y){
        position = new Position(x,y);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition(){
        return position;
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }


}
