import java.io.File;
import java.io.IOException;
import java.util.*;
import guru.nidi.graphviz.attribute.Arrow;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.parse.Parser;

public class RandomGraph{
    LinkedList<GraphEdge> edges = new LinkedList<>();
    LinkedList<GraphVertex> vertices = new LinkedList<>();
    int numVertices;
    Random random = new Random();
    int maxCost = 0;
    RandomGraph(int numVertices){
        for(int i = 0; i<numVertices; ++i){
            vertices.add(new GraphVertex(i));
        }
    }
    public void setMaxEdges(int maxEdges){
        for(int i = 0; i<vertices.size(); ++i){
            vertices.get(i).allowEdges(random.nextInt(maxEdges-1)+1);
        }
    }
    public void setMaxEdges(int minEdges, int maxEdges){
        for(int i = 0; i<vertices.size(); ++i){
            vertices.get(i).allowEdges(random.nextInt(maxEdges+1-minEdges)+minEdges);
        }
    }

    public void createEdges(){
        int [][] probabilityChart = new int[vertices.size()][vertices.size()];
        int edgesLeft=0;
        int randomEdge=0;
        for(int i = 0; i < vertices.size(); ++i){
            for(int j = 0; j < vertices.size(); ++j){
                probabilityChart[j][i]=vertices.get(i).getRemainingEdgeNum()*vertices.get(j).getRemainingEdgeNum();
                if(i==j){
                    probabilityChart[j][i]=0;
                }
            }
        }
        for (int i = 0; i < vertices.size(); ++i) {
            for (int j = 0; j < vertices.size(); ++j) {
                edgesLeft += probabilityChart[j][i];
            }
        }
        while (edgesLeft>0) {
            randomEdge = random.nextInt(edgesLeft);
            for (int i = 0; i < vertices.size(); ++i) {
                for (int j = 0; j < vertices.size(); ++j) {
                    randomEdge -= probabilityChart[j][i];
                    if (randomEdge <= 0) {
                        randomEdge+=edgesLeft;
                        //create edge
                        if (!vertices.get(i).isConnected(vertices.get(j))) {
                            GraphEdge edge = new GraphEdge(vertices.get(i), vertices.get(j));
                            edge.setCost(random.nextInt(maxCost)+1);
                            edges.add(edge);
                            vertices.get(i).addEdge(edge);
                            vertices.get(j).addEdge(edge);
                            for (int k = 0; k < vertices.size(); ++k) {
                                edgesLeft -= probabilityChart[k][i];
                                edgesLeft -= probabilityChart[j][k];
                                probabilityChart[k][i] = vertices.get(k).getRemainingEdgeNum() * vertices.get(i).getRemainingEdgeNum();
                                probabilityChart[j][k] = vertices.get(j).getRemainingEdgeNum() * vertices.get(k).getRemainingEdgeNum();
                                if(i==j){
                                    probabilityChart[j][i]=0;
                                }
                                edgesLeft += probabilityChart[k][i];
                                edgesLeft += probabilityChart[j][k];
                            }
                        }
                    }
                }
            }
        }
    }

    public void displayGraph() {
        System.out.println("Graph Generated:");
        for(int i = 0; i < edges.size(); ++i){
            edges.get(i).displayEdge();
        }
    }

    public void toDotFormat(String fileName) throws IOException {
        StringBuilder dotGraph = new StringBuilder();
        dotGraph.append("graph {");
        String path = "output" + File.separator;
        File folder = new File(path);
        for (int i = 0; i < edges.size(); ++i)
            edges.get(i).toDotFormat(dotGraph);
        dotGraph.append("}");
        MutableGraph graph = new Parser().read(dotGraph.toString());
        graph.graphAttrs()
                .add(Color.WHITE)
                .nodeAttrs()
                .add(Color.WHITE.fill());
        if (!folder.exists())
            folder.mkdirs();
        path += fileName;
        Graphviz.fromGraph(graph).width(700).render(Format.PNG).toFile(new File(path + ".png"));
    }

    public void setMaxEdgeCost(int cost) {
        maxCost = cost;
    }
}
