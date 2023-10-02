package mazerunner.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GraphBasedImperfectMazeGenerator extends Maze implements MazeGenerator {


    public GraphBasedImperfectMazeGenerator(int width, int height,String type) {
        super(width, height,type);
    }

    @Override
    public void generatePath() {
        double i=System.nanoTime();

        List<List<Integer>> keys = new ArrayList<>(graph.keySet());
        Collections.shuffle(keys);
        Random generator = new Random();
        List<Integer> randomWall =  null;
        List<Integer> secondWallList = null;
        List<Integer> secondWall = new ArrayList<Integer>();
        int random = generator.nextInt(101);
        List<Integer> temp;
        int x1=0;   
        int x2=0;
        int y1=0;   
        int y2=0; 
        while(!this.isEntryAndExitConnected()){
            randomWall = keys.get(0);
            secondWallList = graph.get(randomWall);
            random = generator.nextInt(101);
            if(secondWallList!=null){
                if(secondWallList.size() == 4){
                    random%=2;
                    if(random == 0){
                        secondWall.add(0,secondWallList.get(random));
                        secondWall.add(1,secondWallList.get(random+1));
                    }else{
                        secondWall.add(0,secondWallList.get(random+1));
                        secondWall.add(1,secondWallList.get(random+2));
                    }
                    temp=graph.get(randomWall);
                    temp.remove(secondWall.get(0));
                    temp.remove(secondWall.get(1));
                }else{
                    secondWall.add(0,secondWallList.get(0));
                    secondWall.add(1,secondWallList.get(1));
                    graph.remove(randomWall);
                    
                }
                x1=randomWall.get(1);
                x2=secondWall.get(1);
                y1=randomWall.get(0);
                y2=secondWall.get(0);
                Cell cell1 = this.maze.get(y1).get(x1);
                Cell cell2 = this.maze.get(y2).get(x2);
                if(x1 == x2){
                    if(y1 > y2){
                        cell1.setWestWall(1);
                        cell2.setEastWall(1);
                    }else{
                        cell1.setEastWall(1);
                        cell2.setWestWall(1);
                    }
                }else{
                    if(x1 > x2){
                        cell1.setNorthWall(1);
                        cell2.setSouthWall(1);
                    }else{
                        cell1.setSouthWall(1);
                        cell2.setNorthWall(1);
                    }
                }
            
                cell1.setCenter();
                cell2.setCenter();
                int weight1= cell2.getWeight();
                int weight2 = cell1.getWeight();
                if(weight1>weight2)
                { 
                    this.updateWeight(weight1,weight2);
                }else{
                    this.updateWeight(weight2,weight1);
                }
            }
        }
        i=(System.nanoTime()-i)/1000000;
        System.out.println(i);
    }
}
