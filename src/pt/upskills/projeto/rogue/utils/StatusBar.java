package pt.upskills.projeto.rogue.utils;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.*;

import static pt.upskills.projeto.game.Engine.statusTiles;

public class StatusBar {
    public void getStatusBar() {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof Hero) {
                Hero hero = (Hero) tile;

                for (int i = 0; i < 10; i++) {
                    statusTiles.add(new Black(new Position(i, 0)));
                }
                //barra de HP
                for (int i = 3; i < 7; i++) {
                    if (hero.getLife() > (i * 2) - 6) {
                        if (hero.getLife() == ((i - 1) * 2) - 3) {
                            RedGreen redGreen = new RedGreen(new Position(i, 0));
                            statusTiles.add(redGreen);
                            gui.addStatusImage(redGreen);
                        } else {
                            Green green = new Green(new Position(i, 0));
                            statusTiles.add(green);
                            gui.addStatusImage(green);
                        }
                    } else {
                        Red red = new Red(new Position(i, 0));
                        statusTiles.add(red);
                        gui.addStatusImage(red);
                    }
                }
                //barra de vidas
                for (int i = 0; i < 3; i++) {
                    if (hero.getVidas() > i) {
                        Hero vidas = new Hero(new Position(i, 0));
                        statusTiles.add(vidas);
                        gui.addStatusImage(vidas);
                    } else {
                        Black black = new Black(new Position(i, 0));
                        statusTiles.add(black);
                        gui.addStatusImage(black);
                    }
                }
                //barra de itens
                for (int i = 7; i < 10; i++) {
                    Item item = hero.getItens()[i - 7];
                    if (item == null) {
                        break;
                    }
                    item.setPosition(new Position(i, 0));
                    statusTiles.add(item);
                    gui.addStatusImage(item);

                }
            }
        }
    }
}

