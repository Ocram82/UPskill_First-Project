package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Hammer extends Item implements ImageTile {

    public Hammer(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Hammer";
    }
}
