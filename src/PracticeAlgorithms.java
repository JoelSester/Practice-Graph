import java.io.IOException;
import java.util.LinkedList;


public class PracticeAlgorithms {
    public static void main(String[] args) throws IOException {
        RandomGraph randomGraph = new RandomGraph(10);
        MissingNumber missingNumberTest = new MissingNumber();
        ShortestPathFinder shortestPathFinder = new ShortestPathFinder();
        LinkedList<GraphEdge> path;
        GraphVertex startVertex;
        GraphVertex endVertex;
        missingNumberTest.runTests();
        randomGraph.setMaxEdges(3);
        randomGraph.setMaxEdgeCost(10);
        System.out.print("\nCreating edges");
        randomGraph.createEdges();
        System.out.print("\nEdges created");
        randomGraph.toDotFormat("randomGraph");
        //randomGraph.listToConsole();
        startVertex = randomGraph.getRandomVertex();
        endVertex = randomGraph.getRandomVertex();
        while (startVertex==endVertex){
            startVertex = randomGraph.getRandomVertex();
            endVertex = randomGraph.getRandomVertex();
            System.out.print("\n Fetching vertices");
        }
        System.out.println("Path between " + startVertex.getName() + " and " + endVertex.getName() + ":");
        path = shortestPathFinder.dijkstra(startVertex, endVertex);
        if(path==null){
            System.out.println("Path not found");
        } else for(int i = 0; i < path.size(); ++i){
            path.get(i).displayEdge();
        }
    }
}
