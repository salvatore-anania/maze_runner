package mazerunner.generators;

import java.util.ArrayList;
import java.util.List;


public class Cell {
    
    private List<List<Integer>> cell;
    private boolean visited = false;
    private int x;
    private int y;
    private int weight = 0;

    Cell (int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.cell = new ArrayList<List<Integer>>();
        for (int i = 0; i < 3; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < 3; j++) {
                row.add(0);
            }
            this.cell.add(row);
        }
        
    }

    

    public void printLine(int line) {
        List<Integer> currentLine = this.cell.get(line);
        for (int i = 0; i < 3; i++) {
            if(currentLine.get(i) == 1){
                System.out.print(".");
            }else{
                System.out.print("#");
            }
        }
    }

    public void setWall(int x, int y, int value) {
        this.cell.get(x).set(y, value);
    }

    public int getWall(int x, int y) {
        return this.cell.get(x).get(y);
    }

    public void setCenter() {
        this.cell.get(1).set(1, 1);
    }

    public void setWestWall(int value) {
        this.cell.get(1).set(0, value);
    }

    public void setEastWall(int value) {
        this.cell.get(1).set(2, value);
    }

    public void setNorthWall(int value) {
        this.cell.get(0).set(1, value);
    }

    public void setSouthWall(int value) {
        this.cell.get(2).set(1, value);
    }

    public void setVisited(boolean b) {
        this.visited = b;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

}
