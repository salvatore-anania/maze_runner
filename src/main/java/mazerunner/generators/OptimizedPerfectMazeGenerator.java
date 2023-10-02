package mazerunner.generators;



public class OptimizedPerfectMazeGenerator extends Maze implements MazeGenerator{

    public OptimizedPerfectMazeGenerator(int width, int height) {
        super(width, height);
    }
    
    @Override
    public void generatePath() {
        GraphBasedPerfectMazeGenerator maze = new GraphBasedPerfectMazeGenerator(this.width,this.height,"graph");
        double i=System.nanoTime();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                maze.generatePath();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                maze.generatePath();
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            
        }
        i=(System.nanoTime()-i)/1000000;
        System.out.println(i);
        this.maze = maze.maze;
    }

}
