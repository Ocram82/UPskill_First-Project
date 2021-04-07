package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public abstract class Passage implements ImageTile {
        private Position position;
        private static int level=0;

        public Passage(Position position) {
            this.position = position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
        public abstract String getName();

        @Override
        public Position getPosition() {
            return position;
        }
}
