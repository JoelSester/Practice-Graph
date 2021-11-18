public class GraphEdge {
    GraphVertex vertexa;
    GraphVertex vertexb;
    double cost = 1;
    GraphEdge (GraphVertex vertex1, GraphVertex vertex2){
        vertexa=vertex1;
        vertexb=vertex2;
    }
    public GraphVertex getOtherEnd(GraphVertex vertex1){
        if(vertexa == vertex1)
            return vertexb;
        return vertexa;
    }
    public int getCost() {
        return (int) cost;
    }
    public void setCost(int newCost){
        cost = newCost;
    }
    public void displayEdge(){//This function is for debug purposes only
        System.out.println(vertexa.getName() + " -- " + vertexb.getName());
    }

    public void toDotFormat(StringBuilder dotGraph) {
        dotGraph.append(vertexa.getName() + " -- " + vertexb.getName() + "[weight=" + cost + "] [label=" + (int)cost + "];");
    }
}
