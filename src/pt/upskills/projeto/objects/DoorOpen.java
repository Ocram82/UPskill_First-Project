package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class DoorOpen extends Passage implements ImageTile {

    public DoorOpen(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "DoorOpen";
    }

}
