package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class DoorWay extends Passage implements ImageTile {

    public DoorWay(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "DoorWay";
    }
}
