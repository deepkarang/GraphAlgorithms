package graphAlgorithms;

import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Arrays;

public class Maze {

    private int[][] map;

    // Helps us reset the map once we have finished marking it for a particular traversal.
    private int[][] originalMap;

    // Keeps track of total number of nodes visited per traversal.
    // Helps us guage optimality - the less nodes visited to find the shortest path, the most performant the algorithm.
    private int numVisited;

    public Maze(int[][] map) {
        this.map = map;
        this.originalMap = cloneMaze(map);
        this.numVisited = 0;
    }

    public int[] getRandomAccessibleCoordinates() {
        int[] coordinates = new int[2];
        int yCoordinateUpperBound = map.length;
        int xCoordinateUpperBound = map[0].length;
        
        // Randomly generate coordinates.
        Random random = new Random();
        coordinates[0] = random.nextInt(yCoordinateUpperBound);
        coordinates[1] = random.nextInt(xCoordinateUpperBound);

        // Only return positions in the maze we can occupy.
        return map[coordinates[0]][coordinates[1]] == 1 ? coordinates : getRandomAccessibleCoordinates();
    }

    private void recalibrateMaze() {
        // Reset visited counter.
        numVisited = 0;
        // Reset map to what it was pre-traversal.
        map = originalMap;
        originalMap = cloneMaze(map);
    }

    public void runDfsTraversal(int[] startingPoints, int[] targetPoints){
        print("Running DFS...");
        // The only reason we need to pass in an additional array here is to keep track of the path
        // since the function is recursive, on every recursion step we would have a clean slate if we
        // initialized the path array in the function body. Passing this through the recurison stack 
        // allows us to "Remember" where we were.
        dfs(startingPoints[1],startingPoints[0],targetPoints[1],targetPoints[0], new ArrayList<>());
        recalibrateMaze();
    }

    private boolean dfs(int x, int y, int end_x, int end_y, List<String> path){
        if (x<0 || x>map[0].length-1 || y<0 || y>map.length-1) return false;
        if (x==end_x && y==end_y){
            // Mark target node reached.
            map[y][x] = 3; 
            // Finished traversal, so print some basic information about it.
            printTraversalStats(path);
            return true;
        } else {
            if (map[y][x]!=1) return false;
            else {
                // Mark current node as visited.
                map[y][x] = 2;
                numVisited++;

                // Traverse up.
                List<String> pathup = new ArrayList<>(path);
                pathup.add("up");
                if (dfs(x, y-1, end_x, end_y, pathup)) return true;

                // Traverse right.
                List<String> pathright = new ArrayList<>(path);
                pathright.add("right");
                if (dfs(x+1, y, end_x, end_y, pathright)) return true;

                // Traverse down.
                List<String> pathdown = new ArrayList<>(path);
                pathdown.add("down");
                if (dfs(x, y+1, end_x, end_y, pathdown)) return true;

                // Traverse left.
                List<String> pathleft = new ArrayList<>(path);
                pathleft.add("left");
                return dfs(x-1, y, end_x, end_y, pathleft);
            }
        }
    }

    public void runBfsTraversal(int[] startingPoints, int[] targetPoints) {
        print("Running BFS...");
        bfs(startingPoints[1],startingPoints[0],targetPoints[1],targetPoints[0]);
        recalibrateMaze();
    }

    private void bfs(int x, int y, int end_x, int end_y){
        Queue<QueueNode> queue = new LinkedList<>();
        List<String> emptyPath = new LinkedList<>();
        QueueNode q = new QueueNode(new Integer[]{y,x}, emptyPath);
        queue.add(q);
        while (!queue.isEmpty()){
            int levelCount = queue.size();
            QueueNode queueNode = queue.remove();
            Integer[] coordinates = queueNode.getCoordinates();
            List<String> currPath = queueNode.getPath();
            x = coordinates[1];
            y = coordinates[0];
            if (x<0 || x>map[0].length-1 || y<0 || y>map.length-1) continue;
            while (levelCount>0){
                levelCount--;
                if (x==end_x && y==end_y){
                    // Mark target node reached.
                    map[y][x] = 3; 
                    printTraversalStats(currPath);
                    return;
                } else {
                    if (map[y][x]==1) {
                        // Mark as visited.
                        map[y][x] = 2; 
                        numVisited++;

                        List<String> pathup = new ArrayList<>(currPath);
                        pathup.add("up");

                        List<String> pathright = new ArrayList<>(currPath);
                        pathright.add("right");

                        List<String> pathdown = new ArrayList<>(currPath);
                        pathdown.add("down");

                        List<String> pathleft = new ArrayList<>(currPath);
                        pathleft.add("left");

                        QueueNode right = new QueueNode(new Integer[]{y,x+1}, pathright);
                        QueueNode down = new QueueNode(new Integer[]{y+1,x}, pathdown);
                        QueueNode left = new QueueNode(new Integer[]{y,x-1}, pathleft);
                        QueueNode up = new QueueNode(new Integer[]{y-1,x}, pathup);
                        queue.add(right);
                        queue.add(down);
                        queue.add(left);
                        queue.add(up);
                    }
                }
            }
        }
    }

    public void runAStarTraversal(int[] startingPoints, int[] targetPoints) {
        print("Running A* traversal...");
        aStarSearch(startingPoints[1],startingPoints[0],targetPoints[1],targetPoints[0]);
        recalibrateMaze();
    }

    private void aStarSearch(int x, int y, int end_x, int end_y){
        Comparator<QueueNode> comp = (Comparator<QueueNode>) (n1, n2) -> n1.getCost() - n2.getCost();
        PriorityQueue<QueueNode> pq = new PriorityQueue<>(comp);
        List<String> emptyPath = new LinkedList<>();
        QueueNode node = new QueueNode(new Integer[]{y,x}, emptyPath, getHeuristic(x,y,end_x,end_y));
        pq.add(node);
        while (!pq.isEmpty()){
            QueueNode queueNode = pq.remove();
            Integer[] coordinates = queueNode.getCoordinates();
            List<String> currPath = queueNode.getPath();
            x = coordinates[1];
            y = coordinates[0];
            if (x<0 || x>map[0].length-1 || y<0 || y>map.length-1) continue;
            if (x==end_x && y==end_y){
                // Mark target node reached.
                map[y][x] = 3; 
                printTraversalStats(currPath);
                return;
            } else {
                if (map[y][x]==1){
                    // Mark as visited.
                    map[y][x] = 2;
                    numVisited++;

                    List<String> pathup = new ArrayList<>(currPath);
                    pathup.add("up");

                    List<String> pathright = new ArrayList<>(currPath);
                    pathright.add("right");

                    List<String> pathdown = new ArrayList<>(currPath);
                    pathdown.add("down");

                    List<String> pathleft = new ArrayList<>(currPath);
                    pathleft.add("left");

                    QueueNode right = new QueueNode(new Integer[]{y,x+1}, pathright, getHeuristic(x+1,y,end_x,end_y));
                    QueueNode down = new QueueNode(new Integer[]{y+1,x}, pathdown, getHeuristic(x,y+1,end_x,end_y));
                    QueueNode left = new QueueNode(new Integer[]{y,x-1}, pathleft, getHeuristic(x-1,y,end_x,end_y));
                    QueueNode up = new QueueNode(new Integer[]{y-1,x}, pathup, getHeuristic(x,y-1,end_x,end_y));
                    pq.add(right);
                    pq.add(down);
                    pq.add(left);
                    pq.add(up);
                }
            }
        }
    }

    private int[][] cloneMaze(int[][] maze) {
        return Arrays.stream(maze).map(int[]::clone).toArray(int[][]::new);
    }

    /**
     * Heuristic function for A* traversal must output an underestimated quantification of distance to goal.
     * In our case, a straight line is perfect.
     */
    private static int getHeuristic(int x, int y, int end_x, int end_y){
        return (int) Math.sqrt(Math.pow(end_x-x,2) + Math.pow(end_y-y,2));
    }

    private void print(String message) {
        System.out.println(message);
    }

    private void printMaze(){
        for(int i=0; i<map.length; i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }

    private void printTraversalStats(List<String> path) {
        printMaze();
        print("Path from Start to goal node:");
        print(path.toString());
        print("Length of path: " + (path.size() + 1));
        print("Total number of nodes visited: " + numVisited);
        print("---------------------------------------------------------------------------------------");
    }
}

class QueueNode {
    Integer[] coordinates;
    List<String> path;
    int heuristic;
    boolean hasHeuristic = false; // Only used for A* traversal

    QueueNode(Integer[] coordinates, List<String> path){
        this.coordinates = coordinates;
        this.path = path;
    }

    QueueNode(Integer[] coordinates, List<String> path, int heuristic){
        this.coordinates = coordinates;
        this.path = path;
        this.heuristic = heuristic;
        this.hasHeuristic = true;
    }

    List<String> getPath(){
        return this.path;
    }

    Integer[] getCoordinates(){
        return this.coordinates;
    }
    
    int getCost(){
        // A* cost is the cost of current path from start node plus the heuristic from current point
        if (this.hasHeuristic) return this.path.size() + this.heuristic;
        else return this.path.size();
    }
    
}
