import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private Hero hero = new Hero(10, 10);
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private int width,height;
    public Arena(int width,int height){
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonster();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) +
                    1, random.nextInt(height - 2) + 1));
        return coins;
    }

    public void retrieveCoins(){
        for(Coin coin : coins){
            if(coin.getPosition().equals(hero.getPosition())){
                coins.remove(coin);
                break;
            }
        }
    }

    private List<Monster> createMonster() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) +
                    1, random.nextInt(height - 2) + 1));
        return monsters;
    }


    public void setWidth(int width){
        this.width=width;
    }

    public void setHeight(int height){
        this.height=height;
    }

    public boolean verifyMonsterCollisions(Position position){
        for(Monster monster: monsters){
            if(position.equals(monster.getPosition())){
                return true;
            }
        }
        return false;
    }

    public void moveHero(Position position) {
        if(verifyMonsterCollisions(position)){
            System.out.println("GAME OVER");
        }
        if (canHeroMove(position))
            hero.setPosition(position);
        retrieveCoins();
    }

    public void moveMonster(){
        for(Monster monster: monsters){
            monster.move();
        }
    }

    private boolean canHeroMove(Position position) {
        if(position.getX()>=width || position.getX()<=0 || position.getY()>= height || position.getY() <= 0){
            return false;
        }
        for (Wall wall : walls){
            if(wall.getPosition().equals(position))
                return false;
        }
        return true;
    }

    public boolean processKey(KeyStroke key){

        if (key.getKeyType() == KeyType.ArrowUp){
            moveHero(hero.moveUp());
        }
        if (key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());

        }
        if (key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
        }

        if (key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
        }

        if(verifyMonsterCollisions(hero.getPosition())){
            System.out.println("GAME OVER");
            return true;
        }

        moveMonster();
        return false;
    }

    public void draw(TextGraphics graphics) {
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for (Coin coin : coins){
            coin.draw(graphics);
        }
        for(Monster monster : monsters){
            monster.draw(graphics);
        }
        hero.draw(graphics);
    }

    public Hero getHero(){
        return hero;
    }



}
