package mazerunner.generators;

import java.util.ArrayList;
import java.util.List;

public class SimplePerfectMazeGenerator extends Maze implements MazeGenerator{

    public SimplePerfectMazeGenerator(int width, int height) {
        super(width, height);
    }
    
    @Override
    public void generatePath() {
        int currentX = 0;
        int currentY = 0;
        List<Cell> backTrack = new ArrayList<Cell>();
        Cell currentCell = this.maze.get(currentX).get(currentY);
        currentCell.setVisited(true);
        Cell nextCell = null;
        while(!isAllVisited()){
            currentCell.setCenter();
            nextCell = this.getNonVisitedNeighbor(currentX, currentY);
            if(nextCell != null){
                if(nextCell.getX() > currentX){
                    currentCell.setEastWall(1);
                    nextCell.setWestWall(1);
                }else if(nextCell.getX() < currentX){
                    currentCell.setWestWall(1);
                    nextCell.setEastWall(1);
                }else if(nextCell.getY() > currentY){
                    currentCell.setSouthWall(1);
                    nextCell.setNorthWall(1);
                }else if(nextCell.getY() < currentY){
                    currentCell.setNorthWall(1);
                    nextCell.setSouthWall(1);
                }
                currentX = nextCell.getX();
                currentY = nextCell.getY();
                currentCell = this.maze.get(currentX).get(currentY);
                currentCell.setVisited(true);
                backTrack.add(currentCell);
                currentCell.setCenter();
            }else{
                currentCell = backTrack.get(backTrack.size() - 1);
                currentX = currentCell.getX();
                currentY = currentCell.getY();
                backTrack.remove(backTrack.size() - 1);
            }
        }
        currentCell.setCenter();
    }


}
