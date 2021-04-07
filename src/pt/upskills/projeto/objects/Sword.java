package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Sword extends Item implements ImageTile {

    public Sword(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Sword";
    }
}
