package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.*;
import pt.upskills.projeto.rogue.utils.Maping;
import pt.upskills.projeto.rogue.utils.StatusBar;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    public static List<ImageTile> tiles = new ArrayList<>();
    public static List<ImageTile> statusTiles = new ArrayList<>();

    public void init() {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        Maping map = new Maping();
        Hero hero = map.readMapFile(0);

        StatusBar bar = new StatusBar();
        bar.getStatusBar();

        gui.addObserver(hero);
        gui.newImages(tiles);
        gui.newStatusImages(statusTiles);

        gui.go();

        while (true) {
            gui.update();
        }
    }

    public static void main(String[] args) {
        Engine engine = new Engine();
        System.out.println("=================Comandos:==========================");
        System.out.println("W,A,S,D, faz disparar a fireball em uma direccao");
        System.out.println("up, down, left, right, faz mover o heroi e atacar");
        System.out.println("1,2,3, faz drop de item do inventario");
        System.out.println("====================================================");
        engine.init();
    }
}
