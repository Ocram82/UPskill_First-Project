package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.FireTile;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.objects.Explosao;
import pt.upskills.projeto.objects.Sword;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

public class FireBallThread extends Thread {

    private Direction direction;
    private FireTile fireTile;

    public FireBallThread(Direction direction, FireTile fireTile) {
        this.direction = direction;
        this.fireTile = fireTile;
    }

    @Override
    public void run() {
        boolean control = true;
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        while (control) {

            Position nextPosition = fireTile.getPosition().plus(direction.asVector());
            fireTile.setPosition(nextPosition);
            try {
                if (fireTile.validateImpact()) {
                    // FireBall continue
                    sleep(300);
                }else{
                    // FireBall should explode and stop is job
                    Explosao explosao = new Explosao(fireTile.getPosition());
                    gui.addImage(explosao);
                    sleep(500);
                    gui.removeImage(explosao);
                    control = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Remove FireBall of {ImageMatrixGUI}
        ImageMatrixGUI.getInstance().removeImage(fireTile);
    }
}
