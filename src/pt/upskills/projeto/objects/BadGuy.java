package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class BadGuy extends Enemy implements ImageTile {
    private int life=8;
    private int move=4;
    private int damage=2;

    public BadGuy(Position position) {
        super(position);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getMove() {
        return move;
    }

    @Override
    public void setMove(int move) {
        this.move = move;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public String getName() {
        return "BadGuy";
    }
}
