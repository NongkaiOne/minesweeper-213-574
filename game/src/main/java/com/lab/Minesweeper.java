package com.lab;

import java.util.Scanner;
import java.io.InputStream;

public class Minesweeper {
    static char SAFE_CELL = '.';
    static char MINE_CELL = 'X' ;
    static int IS_SAFE = 0;
    static int IS_MINE = 1;
    int fieldX, fieldY;
    int[][] cells;
    String fieldFileName;

    public Minesweeper(String fieldFile) {
        this.fieldFileName = fieldFile;
        initFromFile(fieldFileName);
    }

    public Minesweeper(int fieldX, int fieldY) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.cells = new int[fieldX][fieldY];
        for (int i=0; i<fieldX; i++) {
            for (int j=0; j<fieldY; j++) {
                cells[i][j] = IS_SAFE;
            }
        }
    }

    void displayField() {
        for (int i  = 0 ; i < fieldX ; i++ ){
            for (int j  = 0 ; j < fieldY ; j++ ){
                if (cells[i][j] == IS_MINE){
                    System.out.print(MINE_CELL);
                }else System.out.print(SAFE_CELL);
            }
            System.out.println();
        }
    }
    void setMineCell(int x, int y) {
        cells[x][y] = IS_MINE;
    }

    void initFromFile(String mineFieldFile) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(mineFieldFile);
        
        if (is == null) {
            System.out.println("File not found: " + mineFieldFile);
            return;
        }
    
        try (Scanner a = new Scanner(is)) {
            fieldX = Integer.parseInt(a.nextLine().trim());
            fieldY = Integer.parseInt(a.nextLine().trim());

            cells = new int[fieldX][fieldY];
    
            for (int i = 0; i < fieldX; i++) {
                String line = a.nextLine().trim();
                for (int j = 0; j < fieldY; j++) {
                    if (line.charAt(j) == MINE_CELL) {
                        cells[i][j] = IS_MINE;
                    } else {
                        cells[i][j] = IS_SAFE;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
    
}