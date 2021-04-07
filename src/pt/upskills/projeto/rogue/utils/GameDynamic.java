package pt.upskills.projeto.rogue.utils;

import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.objects.*;

import static pt.upskills.projeto.game.Engine.statusTiles;
import static pt.upskills.projeto.game.Engine.tiles;

public class GameDynamic {
    public void atack(Enemy enemy, int damage) {
        //atacar inimigo
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        enemy.setLife(enemy.getLife() - damage);
        if (enemy.getLife() < 1) {
            loot(enemy);
            gui.removeImage(enemy);
            tiles.remove(enemy);
        }
    }

    public void loot(Enemy enemy) {
        //drop de itens após eliminar inimigo
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        if (enemy instanceof Skeleton) {
            Sword sword = new Sword(enemy.getPosition());
            gui.addImage(sword);
            tiles.add(sword);
        }
        if (enemy instanceof Bat) {
            GoodMeat meat = new GoodMeat(enemy.getPosition());
            gui.addImage(meat);
            tiles.add(meat);
        }
        if (enemy instanceof Thief) {
            Key key = new Key(enemy.getPosition());
            gui.addImage(key);
            tiles.add(key);
        }
        if (enemy instanceof BadGuy) {
            Hammer hammer = new Hammer(enemy.getPosition());
            gui.addImage(hammer);
            tiles.add(hammer);
        }
    }

    public void pickLoot(Item item) {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        gui.removeImage(item);
        tiles.remove(item);
    }

    public void strike(Hero hero, int damage) {
        //atribuir dano ao heroi
        hero.setLife(hero.getLife() - damage);
        //System.out.println(hero.getLife());
        if (hero.getLife() < 1) {
            hero.setVidas(hero.getVidas() - 1);
            if (hero.getVidas() == 0) {
                death();
            } else {
                hero.setLife(8);
                //TODO fazer reset do nivel
            }
        }
    }

    public void death() {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        //perde jogo
        System.out.println("Game Over");
        gui.clearImages();
        //TODo fazer reset ao jogo e voltar ao inicio
    }
    public void levelUp(Passage passage){
        passage.setLevel(passage.getLevel()+1);
    }
    public void levelDown(Passage passage){
        passage.setLevel(passage.getLevel()-1);
    }
}
