package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public abstract class Enemy implements ImageTile {
    //class para todos os inimigos que se mexem
    private Position position;
    private int life;
    private int move;
    private int damage;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Enemy(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public abstract String getName();

    @Override
    public Position getPosition() {
        return position;
    }

}
