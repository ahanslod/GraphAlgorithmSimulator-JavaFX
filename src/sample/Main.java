package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Graph;
import sample.Node;

import java.util.ArrayList;

// How to create a basic JavaFX window:
// 1. Create Stage (Window)
// 2. Rename the window
// 3. Create a GridPane Object to facilitate the creating of a layout
// 4. Define the components on the window
// 5. Add the components to the layout
// 6. Create a scene with the layout we created
// 7. link the scene to the stage
// 8. show the stage to display the window

public class Main extends Application {

    static Graph graph = new Graph();
    static String nodeName;
    static ArrayList<String> nodeNameArray = new ArrayList<String>();
    static ArrayList<Node> nodeArray = new ArrayList<Node>();
    static int countX = 0, countY = 0, countZ;
    public static int nodeCounter = 0;
    public String runOutput = "";
    public static int runCounterDFS = 0;
    public static int runCounterBFS = 0;
    Node node;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Creating a GridPane object (layout)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Setting the padding around the window - 10 pixels all around
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Creating Labels
        Label selectionLabel = new Label("Select your algorithm");

        // Creating radio buttons for DFS and BFS
        ToggleGroup group = new ToggleGroup();
        RadioButton dfs = new RadioButton("DFS");
        RadioButton bfs = new RadioButton("BFS");
        dfs.setToggleGroup(group);
        bfs.setToggleGroup(group);

        // Creating "Results" Label
        Label resultsLabel = new Label("Results");
        Label outputResultsLabel = new Label();
        // Creating "Run" button
        Button runButton = new Button();
        runButton.setText("Run");

        runButton.setOnAction(e -> {
            //What happens when run is clicked with DFS or BFS checked
            outputResultsLabel.setWrapText(true);
            if (dfs.isSelected()) {
                    String DFS = graph.DFSHelper(nodeArray.get(0));
                    outputResultsLabel.setText(DFS);
                    graph.resetNodesVisited();
                    graph.s2 = "";

            } else {
                    String BFS = graph.BFSHelper(nodeArray.get(0));
                    outputResultsLabel.setText(BFS);
                    graph.resetNodesVisited();
                    graph.s1 = "";
            }
        });

        // Creating "Add Node" button
        Button addNodeButton = new Button();
        addNodeButton.setText("Add Node");
        addNodeButton.setOnAction(e -> {
            addNodeWindow();
        });

        // Creating "Add Edge" button
        Button addEdgeButton = new Button();
        addEdgeButton.setText("Add Edge");
        addEdgeButton.setOnAction(e -> {
            addEdgeWindow();
        });

        // Creating "Print Edges" button
        Button printEdgeButton = new Button();
        printEdgeButton.setText("Print Edges");
        printEdgeButton.setOnAction(e -> {
            //print edges consists of printing edges and bfs or dfs
            String copy = graph.printEdgesStr();
            outputResultsLabel.setText("the output of printEdges :\n" + copy + runOutput);

        });

        // Constraining GUI components to grid
        grid.setConstraints(selectionLabel, 0, 0);
        grid.setConstraints(dfs, 0, 1);
        grid.setConstraints(bfs, 1, 1);
        grid.setConstraints(runButton, 0, 2);
        grid.setConstraints(addNodeButton, 8, 0);
        grid.setConstraints(addEdgeButton, 8, 1);
        grid.setConstraints(printEdgeButton, 8, 2);
        grid.setConstraints(resultsLabel, 0, 5);
        grid.setConstraints(outputResultsLabel, 0, 6);


        // Adding components to grid
        grid.getChildren().addAll(selectionLabel, dfs, bfs, runButton, addNodeButton, addEdgeButton, printEdgeButton,
                resultsLabel, outputResultsLabel);

        Scene homeScene = new Scene(grid, 400, 400);
        primaryStage.setTitle("Graph");
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    private static void addEdgeWindow() {
        Stage window = new Stage();
        window.setTitle("Add Edge");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Creating Labels
        Label sourceLabel = new Label("Source");
        Label destinationLabel = new Label("Destination");

        // Creating Text Fields
        TextField sourceField = new TextField();
        // sourceField.getText() to get text entered by the user
        TextField destinationField = new TextField();
        // destinationField.getText() to get text entered by the user

        // Creating "Add" button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            //Find sourceField Node
            //Find destinationField Node
            Node sourceFieldNode = null, destinationFieldNode = null;
            for (String s : nodeNameArray) {
                if (sourceField.getText().equals(s)) {
                    sourceFieldNode = nodeArray.get(countX);
                    break;
                }
                countX++;
            }
            for (String s : nodeNameArray) {
                if (destinationField.getText().equals(s)) {
                    destinationFieldNode = nodeArray.get(countY);
                    break;
                }
                countY++;
            }
            graph.addEdge(sourceFieldNode, destinationFieldNode);
            countX = 0;
            countY = 0;

        });

        grid.getChildren().addAll(sourceLabel, destinationLabel, sourceField, destinationField, addButton);

        grid.setConstraints(sourceLabel, 0, 0);
        grid.setConstraints(destinationLabel, 0, 1);
        grid.setConstraints(sourceField, 3, 0);
        grid.setConstraints(destinationField, 3, 1);
        grid.setConstraints(addButton, 1, 2);

        Scene addNodeScene = new Scene(grid);
        window.setScene(addNodeScene);
        window.show();
    }

    private static void addNodeWindow() {
        Stage window = new Stage();
        window.setTitle("Add Node");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Creating Labels
        Label nodeNameLabel = new Label("Node Name");

        // Creating Text Fields
        TextField nodeNameField = new TextField();
        // nodeNameField.getText() to get text entered by the user

        // Creating "Add" button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            nodeName = nodeNameField.getText();
            Node x = new Node(nodeCounter++, nodeNameField.getText());
            nodeArray.add(x);
            nodeNameArray.add(nodeName);

        });

        grid.getChildren().addAll(nodeNameLabel, nodeNameField, addButton);

        grid.setConstraints(nodeNameLabel, 0, 0);
        grid.setConstraints(nodeNameField, 3, 0);
        grid.setConstraints(addButton, 1, 2);

        grid.getChildren().addAll();
        Scene addEdgeScene = new Scene(grid);
        window.setScene(addEdgeScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
