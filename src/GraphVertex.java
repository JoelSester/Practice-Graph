import java.util.LinkedList;

public class GraphVertex {
    int name;
    int preferredEdges;
    LinkedList<GraphEdge> edges = new LinkedList<>();
    public GraphVertex(int vertexName) {
        name = vertexName;
    }
    public void allowEdges(int maxEdges){
        this.preferredEdges = maxEdges;
    }
    public boolean addEdge(GraphEdge edgeToAdd){
        if(edges.contains(edgeToAdd)) {
            return false;
        }
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
        //System.out.print("\ngetRemainingEdgeNum called " + (preferredEdges - edges.size()) + " edges remaining");
        return preferredEdges -edges.size();
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

    public void listToConsole() {
        System.out.print("\n\nVertex " + name + " Has " + edges.size() + " Edges: ");
        for(int i = 0; i < edges.size(); ++i){
            System.out.print(edges.get(i).getOtherEnd(this).name + ", ");
        }
    }
}
