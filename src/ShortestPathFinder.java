/*
import java.util.LinkedList;

public class ShortestPathFinder {
    public class NodeValuePair{
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
    }
    public LinkedList<GraphEdge> dijkstra(GraphVertex startPoint, GraphVertex endPoint){
        LinkedList<GraphEdge> foundEdges = new LinkedList<>();
        LinkedList<GraphEdge> foundEdges = new LinkedList<>();
        LinkedList<GraphVertex> visited = new LinkedList<>();
        LinkedList<NodeValuePair> toDo = new LinkedList<>();
        GraphEdge currentEdge;
        NodeValuePair current;
        if(startPoint==null||startPoint.getEdgeNum()!=0||startPoint==endPoint)
            return foundEdges;
        toDo.add(new NodeValuePair(0, startPoint, new LinkedList<>()));
        while(){
            current=toDo.get(0);
            if(!visited.contains(current.getNode())) {
                for(int i = 0; i < current.getNode().getEdgeNum(); ++i) {
                    currentEdge = current.getNode().getEdge(i);
                    if(currentEdge!=null){
                        currentEdge.getOtherEnd(current.getNode());
                    }
                }
            }
        }
    }
    /*
    public LinkedList<GraphEdge> bellmanFord(GraphVertex startPoint, GraphVertex endPoint){
        LinkedList<GraphEdge> foundEdges = new LinkedList<>();
        LinkedList<GraphVertex> visited = new LinkedList<>();
    }
     */
//}
