import java.util.Collections;
import java.util.LinkedList;

public class ShortestPathFinder {
    public class NodeValuePair implements Comparable<NodeValuePair>{
        GraphVertex node;
        int distance;
        LinkedList<GraphEdge> path;
        NodeValuePair(int dist, GraphVertex vertex, LinkedList<GraphEdge> path){
            distance = dist;
            node = vertex;
            this.path = path;
        }
        boolean isNode(GraphVertex vertex){
            if (node==vertex)
                return true;
            return false;
        }
        GraphVertex getNode(){
            return node;
        }
        int getDistance(){
            return distance;
        }

        public LinkedList<GraphEdge> getPath() {
            return path;
        }

        @Override
        public int compareTo(NodeValuePair n) {
            return this.getDistance()-n.getDistance();
        }
    }
    public LinkedList<GraphEdge> dijkstra(GraphVertex startPoint, GraphVertex endPoint){
        LinkedList<NodeValuePair> toDo = new LinkedList<>();
        LinkedList<GraphVertex> visited = new LinkedList<>();
        toDo.add(new NodeValuePair(0, startPoint, new LinkedList<>()));
        NodeValuePair current;
        boolean inPath = false;
        while(!visited.contains(endPoint)&&!toDo.isEmpty()){
            Collections.sort(toDo);
            /********************************************************************************/
            //Grab first node on to do list
            current = toDo.getFirst();
            //if the end point is found, return the path
            if (current.getNode()==endPoint)
                return current.getPath();

            //Take each edge the node has and add the other side to to do list
            for(int i = 0; i<current.getNode().getEdgeNum(); ++i){
                //adds last checked node as path, need to check todo
                inPath = false;
                for (NodeValuePair pair: toDo) {
                    if(pair.getNode().getName()==current.getNode().getEdge(i).getOtherEnd(current.getNode()).getName())
                        inPath = true;
                }
                if(!visited.contains(current.getNode().getEdge(i).getOtherEnd(current.getNode())) &&
                        current.getNode().getEdge(i).getOtherEnd(current.getNode())!=startPoint &&
                        !inPath
                        ) {
                    LinkedList<GraphEdge> tempPath = new LinkedList<>(current.getPath());
                    tempPath.add(current.getNode().getEdge(i));
                    toDo.add(new NodeValuePair(
                            current.getDistance() + current.getNode().getEdge(i).getCost(),
                            current.getNode().getEdge(i).getOtherEnd(current.getNode()),
                            tempPath));

                }
            }
            /********************************************************************************/
            //Remove current from to do list and add it to visited list
            toDo.remove(current);
            visited.add(current.getNode());
        }
        return new LinkedList<GraphEdge>();
    }
    /*
    public LinkedList<GraphEdge> bellmanFord(GraphVertex startPoint, GraphVertex endPoint){
        LinkedList<GraphEdge> foundEdges = new LinkedList<>();
        LinkedList<GraphVertex> visited = new LinkedList<>();
    }
     */
}
