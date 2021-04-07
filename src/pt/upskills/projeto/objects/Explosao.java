package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Explosao implements ImageTile {

    private Position position;

    public Explosao(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Fire_old";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

