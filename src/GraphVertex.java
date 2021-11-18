import java.util.LinkedList;
import java.util.List;

public class GraphVertex {
    int name;
    int maxEdges;
    LinkedList<GraphEdge> edges = new LinkedList<>();
    public GraphVertex(int vertexName) {
        name = vertexName;
    }
    public void allowEdges(int maxEdges){
        this.maxEdges = maxEdges;
    }
    public boolean addEdge(GraphEdge edgeToAdd){
        if(edges.contains(edgeToAdd))
            return false;
        if(edges.size()>=maxEdges)
            return false;
        edges.add(edgeToAdd);
        return true;
    }
    public boolean connect(GraphVertex toAdd){
        for(int i = 0; i<edges.size(); ++i){
            if(edges.get(i).getOtherEnd(this)==toAdd)
                return false;
        }
        return addEdge(new GraphEdge(this, toAdd));
    }
    public int getRemainingEdgeNum(){
        return maxEdges-edges.size();
    }

    public boolean isConnected(GraphVertex graphVertex) {
        if(graphVertex==this)
            return true;
        for(int i = 0; i < edges.size(); ++i){
            if(edges.get(i).getOtherEnd(this)==graphVertex)
                return true;
        }
        return false;
    }

    public int getName(){
        return name;
    }

    public int getEdgeNum() {
        return edges.size();
    }

    public GraphEdge getEdge(int i) {
        if(edges.size()<=i)
            return null;
        return edges.get(i);
    }
}
