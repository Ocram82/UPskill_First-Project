package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.FireTile;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.GameDynamic;
import pt.upskills.projeto.rogue.utils.Position;



public class Fireball implements FireTile {
    private Position position;
    private int damage=1;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Fireball(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean validateImpact() {
        //return false quando tocar nalgum objecto
        GameDynamic beta=new GameDynamic();
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof Wall) {
                if (position.equals(tile.getPosition())) {
                    return false;
                }
            }
            if (tile instanceof Enemy) {
                if (position.equals(tile.getPosition())){
                    Enemy enemy = (Enemy) tile;
                    beta.atack(enemy, getDamage());

                    return false;
                }
            }
        }
        return true;
    }
}
