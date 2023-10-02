package mazerunner.generators;

public class SimpleImperfectMazeGenerator extends Maze implements MazeGenerator{


    public SimpleImperfectMazeGenerator(int width, int height) {
        super(width, height);
    }
    
    @Override
    public void generatePath() {
        int currentX = 0;
        int currentY = 0;
        Cell currentCell = this.maze.get(currentX).get(currentY);
        currentCell.setVisited(true);
        Cell nextCell = null;
        while(!isEnd(currentX, currentY)){
            currentCell.setCenter();
            nextCell = this.getRandomNeighbor(currentX, currentY);
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
            }
        }
        currentCell.setCenter();
    }

}

