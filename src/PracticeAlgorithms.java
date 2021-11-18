import java.io.IOException;

public class PracticeAlgorithms {
    public static void main(String[] args) throws IOException {
        RandomGraph randomGraph = new RandomGraph(50);
        MissingNumber missingNumberTest = new MissingNumber();
        missingNumberTest.runTests();
        randomGraph.setMaxEdges(10);
        randomGraph.setMaxEdgeCost(10);
        randomGraph.createEdges();
        randomGraph.toDotFormat("randomGraph");
    }
}
