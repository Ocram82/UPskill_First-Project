package pt.upskills.projeto.rogue.utils;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.*;

import java.util.Random;

public class MoveDynamic {
    public void moveEnemies() {
        //movimento de todos os inimigos
        Random random = new Random();
        GameDynamic beta = new GameDynamic();
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof Enemy) {
                Enemy enemy = (Enemy) tile;
                //System.out.println("Olá sou um esqueleto"+tile.getPosition());
                Position position = null;
                if (enemy.getName().equals("Thief")) {
                    //movimento do thief é como bispo do xadrez
                    switch (random.nextInt(enemy.getMove())) {
                        case 0:
                            if (enemy.getPosition().getY() >= 1 && enemy.getPosition().getX() >= 1) {
                                position = enemy.getPosition().plus(Direction.NW.asVector());
                            }
                            break;
                        case 1:
                            if (enemy.getPosition().getY() >= 1 && enemy.getPosition().getX() <= 8) {
                                position = enemy.getPosition().plus(Direction.NE.asVector());
                            }
                            break;
                        case 2:
                            if (enemy.getPosition().getY() <= 8 && enemy.getPosition().getX() >= 1) {
                                position = enemy.getPosition().plus(Direction.SW.asVector());
                            }
                            break;
                        case 3:
                            if (enemy.getPosition().getY() <= 8 && enemy.getPosition().getX() <= 8) {
                                position = enemy.getPosition().plus(Direction.SE.asVector());
                            }
                            break;
                    }
                } else {
                    switch (random.nextInt(enemy.getMove())) {
                        case 0:
                            if (enemy.getPosition().getX() <= 8) {
                                position = enemy.getPosition().plus(Direction.RIGHT.asVector());
                            }
                            break;
                        case 1:
                            if (enemy.getPosition().getX() >= 1) {
                                position = enemy.getPosition().plus(Direction.LEFT.asVector());
                            }
                            break;
                        case 2:
                            if (enemy.getPosition().getY() >= 1) {
                                position = enemy.getPosition().plus(Direction.UP.asVector());
                            }
                            break;
                        case 3:
                            if (enemy.getPosition().getY() <= 8) {
                                position = enemy.getPosition().plus(Direction.DOWN.asVector());
                            }
                            break;
                    }
                }
                for (ImageTile tile2 : Engine.tiles) {
                    if (tile2 instanceof Wall || tile2 instanceof Passage || tile2 instanceof DoorClosed || tile2 instanceof Enemy || tile2 instanceof Item ||tile2 instanceof GoodMeat||tile2 instanceof StairsUp) {
                        if (position.equals(tile2.getPosition())) {
                            return;
                        }
                    }
                    if (tile2 instanceof Hero) {
                        Hero hero = (Hero) tile2;
                        if (position.equals(tile2.getPosition())) {
                            beta.strike(hero, enemy.getDamage());
                            return;
                        }
                    }
                }
                enemy.setPosition(position);
            } //TODO fazer movimento que aproxime caso heroi por perto
        }
    }
}
