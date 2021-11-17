import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{

    public Monster(int x, int y){
        super(x,y);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#F22222"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()), "M");
    }


    public Position move(){
        Random random = new Random();
        Position newPos = getPosition();
        int maxX = getX()+2, minX = getX()-1, maxY=getY()+2, minY=getY()-1;
        newPos.setX(random.nextInt(maxX-minX)+minX);
        newPos.setY(random.nextInt(maxY-minY)+minY);
        return newPos;
    }


}
