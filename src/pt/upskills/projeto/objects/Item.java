package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public abstract class Item implements ImageTile {
    private Position position;

    public Item(Position position) {
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
