package sample;

import java.util.*;

public class Graph {
    public String s1 = "";
    public String s2 = "";
    private HashMap<Node, LinkedList<Node>> adjacencyMap;

    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        } else tmp = new LinkedList<>();
        tmp.add(b);
        adjacencyMap.put(a, tmp);
    }

    public void addEdge(Node source, Node destination) {

        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);
        addEdgeHelper(destination, source);

    }

    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }

    public void resetNodesVisited() {
        for (Node n : adjacencyMap.keySet()) {
            n.unvisit();
        }

    }

    void BFS(Node node) {

        if (node == null)
            return;

        // Creating the queue, and adding the first node (step 1)
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        String output = "";
        while (!queue.isEmpty()) {
            Node currentFirst = queue.removeFirst();

            if (currentFirst.isVisited())
                continue;

            // Mark the node as visited
            currentFirst.visit();
            output += currentFirst.name + " ";

            LinkedList<Node> allNeighbors = adjacencyMap.get(currentFirst);

            if (allNeighbors == null)
                continue;

            for (Node neighbor : allNeighbors) {
                // We only add unvisited neighbors
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
            }
        }
        System.out.print("the out put of BFS is : " + output);
        System.out.println();

    }

    void BFS_STR(Node node) {

        //implement the BFS code
        //get all neighbors of the input node and go through all the neighbors of it and check whether
        //they have been visited or not using visit(), unvisit(), isVisited() methods in the node class
        //and mark them as visited if you visited them and continue until there is no more unvisited nodes

        // Just so we handle receiving an uninitialized Node, otherwise an
        // exception will be thrown when we try to add it to queue
        if (node == null)
            return;

        // Creating the queue, and adding the first node (step 1)
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currentFirst = queue.removeFirst();

            // In some cases we might have added a particular node more than once before
            // actually visiting that node, so we make sure to check and skip that node if we have
            // encountered it before
            if (currentFirst.isVisited())
                continue;

            // Mark the node as visited
            currentFirst.visit();
            s1 += currentFirst.name + " ";

            LinkedList<Node> allNeighbors = adjacencyMap.get(currentFirst);

            // We have to check whether the list of neighbors is null before proceeding, otherwise the for-each loop will throw an exception
            if (allNeighbors == null)
                continue;

            for (Node neighbor : allNeighbors) {
                // We only add unvisited neighbors
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
            }
        }

    }

    public String BFSHelper(Node node) {
        BFS_STR(node);
        return "the output of BFS is : " + s1;
    }

    public int count = 0;
    public String output = "the output of DFS is : ";

    public void DFS(Node node) {
        // Implement DFS
        node.visit();
        if (count == 0) {
            System.out.print(output);
            count++;
        }
        System.out.print(node.name + " ");

        LinkedList<Node> allNeighbors = adjacencyMap.get(node);
        if (allNeighbors == null)
            return;

        for (Node neighbor : allNeighbors) {
            if (!neighbor.isVisited())
                DFS(neighbor);
        }
        // recursive approach

    }

    public void DFS_STR(Node node) {
        //Implement DFS
        node.visit();
        s2 += node.name + " ";

        LinkedList<Node> allNeighbors = adjacencyMap.get(node);
        if (allNeighbors == null)
            return;

        for (Node neighbor : allNeighbors) {
            if (!neighbor.isVisited())
                DFS_STR(neighbor);
        }
        //recursive approach

    }

    public String DFSHelper(Node node) {
        DFS_STR(node);
        return "the output of DFS is : " + s2;
    }

    public void printEdges() {
        //implement printEdges
        for (Node node : adjacencyMap.keySet()) {
            System.out.print("The " + node.name + " has an edge towards: ");
            for (Node neighbor : adjacencyMap.get(node)) {
                System.out.print(neighbor.name + " ");
            }
            System.out.println();
        }

    }

    public String printEdgesStr() {
        String str = "";
        for (Node node : adjacencyMap.keySet()) {
            String str1 = "The " + node.name + " has an edge towards: ";
            String str2 = "";
            for (Node neighbor : adjacencyMap.get(node)) {
                str2 += neighbor.name + " ";
            }
            String str3 = "\n";

            str += str1 + str2 + str3;
        }
        return str;
    }
}


