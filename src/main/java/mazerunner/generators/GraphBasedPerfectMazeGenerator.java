package mazerunner.generators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GraphBasedPerfectMazeGenerator extends Maze implements MazeGenerator {

    public GraphBasedPerfectMazeGenerator(int width, int height,String type) {
        super(width, height,type);
    }
    
    @Override
    public void generatePath() {
        double i=System.nanoTime();
        List<Integer> walls[] =new List[graph.size()];
        Iterator<List<Integer>> itr = graph.keySet().iterator();
        int test=0;
        while (itr.hasNext())
        {
            walls[test] = itr.next();
            test++;
        }
        Random generator = new Random();
        List<Integer> randomWall =  walls[generator.nextInt(walls.length)];
        List<Integer> secondWallList = graph.get(randomWall);
        List<Integer> secondWall = new ArrayList<Integer>();
        int random = generator.nextInt(101);
        while(!graph.isEmpty()){
            randomWall =  walls[generator.nextInt(walls.length)];
            secondWallList = graph.get(randomWall);
            secondWall = new ArrayList<Integer>();
            random = generator.nextInt(101);
            if(secondWallList.size() == 4){
                random%=2;
                if(random == 0){
                    secondWall.add(secondWallList.get(random));
                    secondWall.add(secondWallList.get(random+1));
                }else{
                    secondWall.add(secondWallList.get(random+1));
                    secondWall.add(secondWallList.get(random+2));
                }
                graph.get(randomWall).remove(secondWall.get(0));
                graph.get(randomWall).remove(secondWall.get(1));
            }else{
                secondWall.add(secondWallList.get(0));
                secondWall.add(secondWallList.get(1));
                graph.remove(randomWall);
            }
            Cell cell1 = this.maze.get(randomWall.get(0)).get(randomWall.get(1));
            Cell cell2 = this.maze.get(secondWall.get(0)).get(secondWall.get(1));
            if(cell2.getWeight()!=cell1.getWeight()){
                if(randomWall.get(1) == secondWall.get(1)){
                    if(randomWall.get(0) > secondWall.get(0)){
                        cell1.setWestWall(1);
                        cell2.setEastWall(1);
                    }else{
                        cell1.setEastWall(1);
                        cell2.setWestWall(1);
                    }
                }else{
                    if(randomWall.get(1) > secondWall.get(1)){
                        cell1.setNorthWall(1);
                        cell2.setSouthWall(1);
                    }else{
                        cell1.setSouthWall(1);
                        cell2.setNorthWall(1);
                    }
                }
            }
            cell1.setCenter();
            cell2.setCenter();
            if(cell2.getWeight()>cell1.getWeight())
            {
                int oldWeight= cell2.getWeight();
                int newWeight = cell1.getWeight();
                this.updateWeight(oldWeight,newWeight);
            }else{
                int oldWeight= cell1.getWeight();
                int newWeight = cell2.getWeight();
                this.updateWeight(oldWeight,newWeight);
            }
        }
    }


}
