package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.game.FireBallThread;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.*;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import static pt.upskills.projeto.game.Engine.statusTiles;
import static pt.upskills.projeto.game.Engine.tiles;

public class Hero implements ImageTile, Observer {

    private Position position;
    private static int life = 8;
    private static int vidas = 3;
    private static int damage = 1;
    private static Item[] itens = new Item[3];


    public Hero(Position position) {
        this.position = position;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public Item[] getItens() {
        return itens;
    }

    public void setItens(Item[] itens) {
        this.itens = itens;
    }

    public int getDamage() {
        for (int i = 0; i < 3; i++) {
            if (itens[i] instanceof Sword) {
                damage += 1;
            } else if (itens[i] instanceof Hammer) {
                damage += 2;
            }
        }
        return damage;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * This method is called whenever the observed object is changed. This function is called when an
     * interaction with the graphic component occurs {{@link ImageMatrixGUI}}
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        MoveDynamic alfa = new MoveDynamic();
        GameDynamic beta = new GameDynamic();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance(); //singleton
        alfa.moveEnemies(); //move todos os inimigos
        StatusBar bar = new StatusBar();

        for (ImageTile tile : Engine.tiles) {
            tile.getPosition();
        }

        Position newPosition = null;
        Integer keyCode = (Integer) arg;
        if (keyCode == KeyEvent.VK_DOWN) {
            if (getPosition().getY() <= 8) {
                newPosition = position.plus(Direction.DOWN.asVector());
            }
        }
        if (keyCode == KeyEvent.VK_UP) {
            if (getPosition().getY() >= 1) {
                newPosition = position.plus(Direction.UP.asVector());
            }
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            if (getPosition().getX() >= 1) {
                newPosition = position.plus(Direction.LEFT.asVector());
            }
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            if (getPosition().getX() <= 8) {
                newPosition = position.plus(Direction.RIGHT.asVector());
            }
        }

        if (newPosition != null) {
            for (ImageTile tile : Engine.tiles) {
                if (tile instanceof Wall) {
                    if (newPosition.equals(tile.getPosition())) {
                        return;
                    }
                }
                if (tile instanceof Enemy) {
                    Enemy enemy = (Enemy) tile;
                    if (newPosition.equals(tile.getPosition())) {
                        beta.atack(enemy, getDamage());
                        return;
                    }
                }
                if (tile instanceof Trap) {
                    if (newPosition.equals(tile.getPosition())) {
                        setTrap();
                    }
                }
                if (tile instanceof GoodMeat) {
                    GoodMeat meat = (GoodMeat) tile;
                    if (newPosition.equals(tile.getPosition())) {
                        setMeal();
                        gui.removeImage(meat);
                        tiles.remove(meat);
                        break;
                    }
                }
                if (tile instanceof Item) {
                    Item item = (Item) tile;
                    if (newPosition.equals(tile.getPosition())) {
                        for (int i = 0; i < 3; i++) {
                            if (itens[i] == null) {
                                itens[i] = item;
                                beta.pickLoot(item);
                                position = newPosition;
                                bar.getStatusBar();
                                return;
                            }
                        }
                    }
                }
                if (tile instanceof DoorOpen) {
                    DoorOpen doorOpen = (DoorOpen) tile;
                    if (newPosition.equals(tile.getPosition())) {
                        Maping map = new Maping();
                        //map.saveMapFile(doorOpen.getLevel());
                        beta.levelUp(doorOpen);
                        gui.clearImages();
                        tiles.clear();
                        gui.deleteObservers();
                        Hero hero = map.readMapFile(doorOpen.getLevel());
                        gui.newImages(tiles);
                        gui.addObserver(hero);
                        return;
                    }
                    //todo save

                }
                if (tile instanceof DoorClosed) {
                    DoorClosed doorClosed = (DoorClosed) tile;

                    if (newPosition.equals(tile.getPosition())) {
                        for (int i = 0; i < 3; i++) {
                            if (itens[i] instanceof Key) {
                                itens[i] = null;
                                Black black = new Black(new Position(i + 7, 0));
                                statusTiles.add(black);
                                gui.addStatusImage(black);
                                tiles.remove(doorClosed);
                                gui.removeImage(doorClosed);
                                DoorOpen doorOpen = new DoorOpen(newPosition);
                                tiles.add(doorOpen);
                                gui.addImage(doorOpen);
                                return;
                            }
                        }
                    }
                }
                if (tile instanceof StairsUp) {
                    StairsUp stairsUp = (StairsUp) tile;
                    if (newPosition.equals(tile.getPosition())) {
                        System.out.println("YOU WIN");
                        System.out.println("======LeaderBoard:=======");
                        gui.clearImages();
                        break;
                    }
                    //todo leaderboard


                }
                if (tile instanceof DoorWay) {
                    DoorWay doorWay = (DoorWay) tile;
                    if (newPosition.equals(tile.getPosition())) {
                        Maping map = new Maping();
                        beta.levelDown(doorWay);
                        gui.clearImages();
                        tiles.clear();
                        gui.deleteObservers();
                        //Hero hero= map.readSavedMap(doorWay.getLevel());
                        Hero hero = map.readMapFile(doorWay.getLevel());
                        gui.newImages(tiles);
                        gui.addObserver(hero);
                        return;
                    }
                    //todo ler save gravado
                }
            }
            position = newPosition;
        }

        //outras teclas
        if (keyCode == KeyEvent.VK_W) {
            Fireball fireBall = new Fireball(position);
            FireBallThread fireBallThread = new FireBallThread(Direction.UP, fireBall);
            fireBallThread.start();
            Engine.tiles.add(fireBall);
            gui.addImage(fireBall);
        }
        if (keyCode == KeyEvent.VK_S) {
            Fireball fireBall = new Fireball(position);
            FireBallThread fireBallThread = new FireBallThread(Direction.DOWN, fireBall);
            fireBallThread.start();
            Engine.tiles.add(fireBall);
            gui.addImage(fireBall);
        }
        if (keyCode == KeyEvent.VK_A) {
            Fireball fireBall = new Fireball(position);
            FireBallThread fireBallThread = new FireBallThread(Direction.LEFT, fireBall);
            fireBallThread.start();
            Engine.tiles.add(fireBall);
            gui.addImage(fireBall);
        }
        if (keyCode == KeyEvent.VK_D) {
            Fireball fireBall = new Fireball(position);
            FireBallThread fireBallThread = new FireBallThread(Direction.RIGHT, fireBall);
            fireBallThread.start();
            Engine.tiles.add(fireBall);
            gui.addImage(fireBall);
        }
        //commandos para dropar item do inventario
        if (keyCode == KeyEvent.VK_1) {
            if (itens[0] == null) {
                return;
            } else {
                Item item = itens[0];
                statusTiles.remove(item);
                gui.removeStatusImage(item);
                item.setPosition(position);
                tiles.add(item);
                gui.addImage(item);
                itens[0] = null;
            }
        }
        if (keyCode == KeyEvent.VK_2) {
            if (itens[1] == null) {
                return;
            } else {
                Item item = itens[1];
                item.setPosition(position);
                statusTiles.remove(item);
                gui.removeStatusImage(item);
                tiles.add(item);
                gui.addImage(item);
                itens[1] = null;
            }
        }
        if (keyCode == KeyEvent.VK_3) {
            if (itens[2] == null) {
                return;
            } else {
                Item item = itens[2];
                item.setPosition(position);
                statusTiles.remove(item);
                gui.removeStatusImage(item);
                tiles.add(item);
                gui.addImage(item);
                itens[2] = null;
            }
        }
        bar.getStatusBar();
    }

    public void setTrap() {
        GameDynamic beta = new GameDynamic();
        setLife(getLife() - 1);
        if (getLife() < 1) {
            setVidas(getVidas() - 1);
            if (getVidas() == 0) {
                beta.death();
            } else {
                setLife(8);
            }
        }
    }

    public void setMeal() {
        setLife(getLife() + 5);
        if (getLife() > 8) {
            setLife(8);
        }
    }
}

