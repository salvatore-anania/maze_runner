package mazerunner.generators;
import java.util.ArrayList;

import java.util.List;
import java.util.HashMap;

public class Maze{

    int width;
    int height;
    List<List<Cell>> maze;
    HashMap<List<Integer>,List<Integer>> graph;
    
    

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        int weight = 0;
        this.maze = new ArrayList<List<Cell>>();
        for (int i = 0; i < width; i++) {
            List<Cell> row = new ArrayList<Cell>();
            for (int j = 0; j < height; j++) {
                if(i == 0 && j == 0 ){
                    Cell cell = new Cell(i, j, weight);
                    cell.setWall(0, 1, 1);
                    row.add(cell);
                }else if(i == width - 1 && j == height - 1){
                    Cell cell = new Cell(i, j, weight);
                    cell.setWall(2, 1, 1);
                    row.add(cell);
                }else{
                    row.add(new Cell(i, j, weight));
                }
                weight++;
            }
            this.maze.add(row);
        }
    }

    public Maze(int width, int height,String type) {
        this.width = width;
        this.height = height;
        this.graph = new HashMap<List<Integer>,List<Integer>>();
        this.maze = new Maze(width,height).maze;
        for (int i = 0; i < width; i++) {
            List<Cell> row = new ArrayList<Cell>();
            for (int j = 0; j < height; j++) {
                if(i == width-1){
                    if(j != height-1){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(j);
                        List<Integer> list2 = new ArrayList<Integer>();
                        list2.add(i);
                        list2.add(j+1);
                        this.graph.put(list, list2);
                    }
                }else if(j == height-1){
                    if(i != width-1){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(j);
                        List<Integer> list2 = new ArrayList<Integer>();
                        list2.add(i+1);
                        list2.add(j);
                        this.graph.put(list, list2);
                    }                    
                }else{
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(j);
                    List<Integer> list2 = new ArrayList<Integer>();
                    list2.add(i+1);
                    list2.add(j);
                    list2.add(i);
                    list2.add(j+1);
                    this.graph.put(list, list2);
                }
            }
            this.maze.add(row);
        }
    }

    public void printMaze(){
        for (int j = 0; j < this.height ; j++) {
            for (int line = 0; line < 3 ; line++) {
                for (int i = 0; i < this.width; i++) {
                    this.maze.get(i).get(j).printLine(line);
                }
                System.out.println();
            }
            
        }
    
    }

    public boolean isAllVisited() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height ; j++) {
                if(!this.maze.get(i).get(j).isVisited()){
                    return false;
                }
            }
        }
        return true;
    }

    public void setVisited(int x, int y) {
        this.maze.get(x).get(y).setVisited(true);
    }

    public boolean isVisited(int x, int y) {
        return this.maze.get(x).get(y).isVisited();
    }

    public boolean isEnd(int x, int y) {
        return x == this.width - 1 && y == this.height - 1;
    }

    public Cell getRandomNeighbor(int x, int y){
        List<Cell> neighbors = new ArrayList<Cell>();
        if(x > 0){
            if(!this.maze.get(x - 1).get(y).isVisited()){
                neighbors.add(this.maze.get(x - 1).get(y));
            }
        }
        if(x < this.width - 1){
            neighbors.add(this.maze.get(x + 1).get(y));
        }
        if(y > 0){
            neighbors.add(this.maze.get(x).get(y - 1));
        }
        if(y < this.height - 1){
            neighbors.add(this.maze.get(x).get(y + 1));
        }
        int random = (int) (Math.random() * neighbors.size());
        return neighbors.get(random);
    }


    public Cell getNonVisitedNeighbor(int x, int y){
        List<Cell> neighbors = new ArrayList<Cell>();
        if(x > 0){
            if(!this.maze.get(x - 1).get(y).isVisited()){
                neighbors.add(this.maze.get(x - 1).get(y));
            }
        }
        if(x < this.width - 1){
            if(!this.maze.get(x + 1).get(y).isVisited()){
                neighbors.add(this.maze.get(x + 1).get(y));
            }
        }
        if(y > 0){
            if(!this.maze.get(x).get(y - 1).isVisited()){
                neighbors.add(this.maze.get(x).get(y - 1));
            }
        }
        if(y < this.height - 1){
            if(!this.maze.get(x).get(y + 1).isVisited()){
                neighbors.add(this.maze.get(x).get(y + 1));
            }
        }
        int random = (int) (Math.random() * neighbors.size());
        if(neighbors.size() == 0){
            return null;
        }
        return neighbors.get(random);
    }

    public Cell getRandomCell(){
        int randomX = (int) (Math.random() * this.width);
        int randomY = (int) (Math.random() * this.height);
        return this.maze.get(randomX).get(randomY);
    }

    public boolean isEntryAndExitConnected(){
        if(this.maze.get(this.width - 1).get(this.height - 1).getWeight() == 0){
            return true;
        }else{
            return false;
        }
    }
    
    public void updateWeight(int oldweight, int newWeight){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height ; j++) {
                if(this.maze.get(i).get(j).getWeight() == oldweight){
                    this.maze.get(i).get(j).setWeight(newWeight);
                }
            }
        }
    }
    
}
