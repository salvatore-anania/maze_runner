package mazerunner;

import mazerunner.generators.GraphBasedImperfectMazeGenerator;
import mazerunner.generators.GraphBasedPerfectMazeGenerator;
import mazerunner.generators.OptimizedImperfectMazeGenerator;
import mazerunner.generators.OptimizedPerfectMazeGenerator;
import mazerunner.generators.SimpleImperfectMazeGenerator;
import mazerunner.generators.SimplePerfectMazeGenerator;

public class Main {
    
    public static void main(String[] args) {
        try {
            if(args.length != 4){
                System.out.println("Utilisation : java -jar Maze.jar [largeur] [hauteur] [perfect/imperfect] [simple/graph/optimized]");
                System.exit(-1);
            }
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
            if(width < 5 || height < 5){
                System.out.println("La largeur et la hauteur doivent être supérieures à 5");
            }else if(!args[2].equals("perfect") && !args[2].equals("imperfect")){ 
                System.out.println(args[2]);
                System.out.println("Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.");
            }else if(!args[3].equals("simple") && args[3].equals("graph") && args[3].equals("optimized")){
                System.out.println("Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.");
            }else{
                if(args[2].equals("perfect")){
                    if(args[3].equals("simple")){
                        SimplePerfectMazeGenerator maze = new SimplePerfectMazeGenerator(width, height);
                        maze.generatePath();
                        maze.printMaze();
                    }else if(args[3].equals("graph")){
                        GraphBasedPerfectMazeGenerator maze = new GraphBasedPerfectMazeGenerator(width, height, "graph");
                        maze.generatePath();
                        maze.printMaze();
                    }else{
                        OptimizedPerfectMazeGenerator maze = new OptimizedPerfectMazeGenerator(width, height);
                        maze.generatePath();
                        maze.printMaze();
                    }
                }else{
                    if(args[3].equals("simple")){
                        SimpleImperfectMazeGenerator maze = new SimpleImperfectMazeGenerator(width, height);
                        maze.generatePath();
                        maze.printMaze();
                    }else if(args[3].equals("graph")){
                        GraphBasedImperfectMazeGenerator maze = new GraphBasedImperfectMazeGenerator(width, height,"graph");
                        maze.generatePath();
                        System.out.println("maze");
                        maze.printMaze();
                    }else{
                        OptimizedImperfectMazeGenerator maze = new OptimizedImperfectMazeGenerator(width, height);
                        maze.generatePath();
                        maze.printMaze();
                    }
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("Les deux premiers arguments doivent être des entiers");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());      
        }
    }
}
