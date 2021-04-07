package pt.upskills.projeto.rogue.utils;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.*;

import static pt.upskills.projeto.game.Engine.tiles;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Maping {

    public Hero readMapFile(int level) {
        //criar o mapa
        //ler o ficheiro rooms/room + level+.txt
        Hero hero = null;
        try {
            Scanner fileScanner = new Scanner(new File("rooms/room" + level + ".txt"));
            //criar chao
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    //System.out.println(i+", "+j);
                    tiles.add(new Floor(new Position(i, j)));
                }
            }

            int j = 0;
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                String[] separar = nextLine.split("");
                //criar ground objects sem prioridade
                for (int i = 0; i < separar.length; i++) {
                    switch (separar[i]) {
                        case "t":
                            tiles.add(new Trap(new Position(i, j)));
                            break;
                        case "g":
                            tiles.add(new Grass(new Position(i, j)));
                            break;
                    }
                }
                j++;
            }
            fileScanner = new Scanner(new File("rooms/room" + level + ".txt"));
            j = 0;
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                String[] separar = nextLine.split("");
                //criar restantes objectos que passam por cima
                for (int i = 0; i < separar.length; i++) {
                    switch (separar[i]) {
                        case "W":
                            tiles.add(new Wall(new Position(i, j)));
                            break;
                        case "S":
                            tiles.add(new Skeleton(new Position(i, j)));
                            break;
                        case "0":
                            tiles.add(new DoorOpen(new Position(i, j)));
                            break;
                        case "s":
                            tiles.add(new Sword(new Position(i, j)));
                            break;
                        case "1":
                            tiles.add(new DoorClosed(new Position(i, j)));
                            break;
                        case "B":
                            tiles.add(new Bat(new Position(i, j)));
                            break;
                        case "G":
                            tiles.add(new BadGuy(new Position(i, j)));
                            break;
                        case "k":
                            tiles.add(new Key(new Position(i, j)));
                            break;
                        case "T":
                            tiles.add(new Thief(new Position(i, j)));
                            break;
                        case "m":
                            tiles.add(new GoodMeat(new Position(i, j)));
                            break;
                        case "H":
                            tiles.add(new Hammer(new Position(i, j)));
                            break;
                        case "3":
                            tiles.add(new StairsUp(new Position(i, j)));
                            break;
                        case "2":
                            DoorWay doorWay = new DoorWay(new Position(i, j));
                            tiles.add(doorWay);
                            hero = new Hero(new Position(i, j));
                            tiles.add(hero);
                            break;
                        case "h":
                            hero = new Hero(new Position(i, j));
                            tiles.add(hero);
                            break;
                    }
                }
                j++;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possivel ler o ficheiro do nivel" + level);
        }
        //return de hero para utilização em engine
        return hero;
    }
    /*
    public void saveMapFile(int level) {
        try {
            FileWriter file = new FileWriter("rooms/saveRoom" + level + ".txt", true);
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(file));
            int i = 1;
            for (ImageTile tile : Engine.tiles) {
                printWriter.println(tiles);
                i++;
                printWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    /*
    public Hero readSavedMap(int level){
        Hero hero = null;
        try {
            Scanner fileScanner = new Scanner(new File("rooms/saveRoom" + level + ".txt"));
            //criar chao
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    //System.out.println(i+", "+j);
                    tiles.add(new Floor(new Position(i, j)));
                }
            }

            int j = 0;
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                String[] separar = nextLine.split("");
                //criar ground objects sem prioridade
                for (int i = 0; i < separar.length; i++) {
                    switch (separar[i]) {
                        case "t":
                            tiles.add(new Trap(new Position(i, j)));
                            break;
                        case "g":
                            tiles.add(new Grass(new Position(i, j)));
                            break;
                    }
                }
                j++;
            }
            fileScanner = new Scanner(new File("rooms/room" + level + ".txt"));
            j = 0;
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                String[] separar = nextLine.split("");
                //criar restantes objectos que passam por cima
                for (int i = 0; i < separar.length; i++) {
                    switch (separar[i]) {
                        case "W":
                            tiles.add(new Wall(new Position(i, j)));
                            break;
                        case "S":
                            tiles.add(new Skeleton(new Position(i, j)));
                            break;
                        case "0":
                            tiles.add(new DoorOpen(new Position(i, j)));
                            break;
                        case "s":
                            tiles.add(new Sword(new Position(i, j)));
                            break;
                        case "1":
                            tiles.add(new DoorClosed(new Position(i, j)));
                            break;
                        case "B":
                            tiles.add(new Bat(new Position(i, j)));
                            break;
                        case "G":
                            tiles.add(new BadGuy(new Position(i, j)));
                            break;
                        case "k":
                            tiles.add(new Key(new Position(i, j)));
                            break;
                        case "T":
                            tiles.add(new Thief(new Position(i, j)));
                            break;
                        case "m":
                            tiles.add(new GoodMeat(new Position(i, j)));
                            break;
                        case "H":
                            tiles.add(new Hammer(new Position(i, j)));
                            break;
                        case "3":
                            tiles.add(new StairsUp(new Position(i, j)));
                            break;
                        case "2":
                            DoorWay doorWay = new DoorWay(new Position(i, j));
                            tiles.add(doorWay);
                            break;
                        case "h":
                            hero = new Hero(new Position(i, j));
                            tiles.add(hero);
                            break;
                    }
                }
                j++;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possivel ler o ficheiro do nivel" + level);
        }
        return hero;
    }*/
}

