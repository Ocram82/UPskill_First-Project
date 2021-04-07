package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Key extends Item implements ImageTile {

    public Key(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Key";
    }
}