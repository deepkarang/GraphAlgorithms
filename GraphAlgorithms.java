import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Arrays;

class GraphAlgorithms {
    public static void main(String[] args) {

        // Define sample maze with '1' indicating a permissible position and '0' indicating a blockade.
        int[][] map = { { 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0 },
                { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1 },
                { 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

        // Define starting position.
        int[] start = {13,2};

        // Define target position or goal state.
        int[] end = {5,23};

        System.out.println("Running DFS...");
        int[][] dfsmap = getCopyOfMatrix(map);
        List<String> dfspath = new ArrayList<>();
        IntWrapper dfsNumVisited = new IntWrapper(0);
        dfs(dfsmap,start[1],start[0],end[1],end[0],dfsNumVisited,dfspath);
        printMatrix(dfsmap);
        System.out.println("Nodes visited in total: " + dfsNumVisited.toString());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Running BFS...");
        int[][] bfsmap = getCopyOfMatrix(map);
        bfs(bfsmap,start[1],start[0],end[1],end[0]);
        printMatrix(bfsmap);
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Running A* search...");
        int[][] astarmap = getCopyOfMatrix(map);
        printMatrix(astarmap);
        aStarSearch(astarmap,start[1],start[0],end[1],end[0]);
    }


    private static boolean dfs(int[][] map, int x, int y, int end_x, int end_y, IntWrapper numVisited, List<String> path){
        if (x<0 || x>map[0].length-1 || y<0 || y>map.length-1) return false;
        if (x==end_x && y==end_y){
            map[y][x] = 3; //Mark end position
            System.out.println("Path from Start to goal node:");
            System.out.println(path);
            int cost = path.size() + 1;
            System.out.println("Cost of path (number of moves): " + cost);
            return true;
        } else {
            if (map[y][x]!=1) return false;
            else {
                map[y][x] = 2; //mark as visited
                numVisited.increment();
                //traverse up
                List<String> pathup = new ArrayList<>(path);
                pathup.add("up");
                if (dfs(map,x,y-1,end_x,end_y, numVisited, pathup)) return true;

                //traverse right
                List<String> pathright = new ArrayList<>(path);
                pathright.add("right");
                if (dfs(map, x+1,y,end_x,end_y, numVisited, pathright)) return true;

                //traverse down
                List<String> pathdown = new ArrayList<>(path);
                pathdown.add("down");
                if (dfs(map,x,y+1,end_x,end_y, numVisited, pathdown)) return true;

                //traverse left
                List<String> pathleft = new ArrayList<>(path);
                pathleft.add("left");
                return dfs(map, x - 1, y, end_x, end_y, numVisited, pathleft);
            }
        }
    }

    private static void bfs(int[][] map, int x, int y, int end_x, int end_y){
        Queue<QueueNode> queue = new LinkedList<>();
        List<String> emptyPath = new LinkedList<>();
        QueueNode q = new QueueNode(new Integer[]{y,x}, emptyPath);
        queue.add(q);
        int numVisited = 0;
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
                    map[y][x] = 3; //Mark end position
                    System.out.println("Path from Start to goal node:");
                    System.out.println(currPath);
                    int cost = currPath.size() + 1;
                    System.out.println("Cost of path (number of moves): " + cost);
                    System.out.println("Nodes visited in total: " + numVisited);
                    return;
                } else {
                    if (map[y][x]==1) {
                        numVisited++;
                        map[y][x] = 2; //mark as visited

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

    private static void aStarSearch(int[][] map, int x, int y, int end_x, int end_y){
        Comparator<QueueNode> comp = (Comparator<QueueNode>) (n1, n2) -> n1.getCost() - n2.getCost();
        PriorityQueue<QueueNode> pq = new PriorityQueue<>(comp);
        int numVisited = 0;
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
                map[y][x] = 3; //Mark end position
                System.out.println("Path from Start to goal node:");
                System.out.println(currPath);
                int cost = currPath.size() + 1;
                System.out.println("Cost of path (number of moves): " + cost);
                System.out.println("Nodes visited in total: " + numVisited);
                return;
            } else {
                if (map[y][x]==1){
                    numVisited++;
                    map[y][x] = 2; //mark as visited

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

    private static int[][] getCopyOfMatrix(int[][] map){
        int[][] res = new int[map.length][map[0].length];
        for(int i=0; i<map.length; i++) System.arraycopy(map[i], 0, res[i], 0, map[i].length);
        return res;
    }

    private static void printMatrix(int[][] map){
        for(int i=0; i<map.length; i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }

    /**
     * Heuristic function for A* traversal must output an underestimated quantification of distance to goal.
     * In our case, a straight line is perfect.
     */
    private static int getHeuristic(int x, int y, int end_x, int end_y){
        return (int) Math.sqrt(Math.pow(end_x-x,2) + Math.pow(end_y-y,2));
    }

    private static class QueueNode {
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

    private static class IntWrapper {
        public Integer value;

        IntWrapper(Integer value) {
            this.value = value;
        }

        void increment(){
            this.value++;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

