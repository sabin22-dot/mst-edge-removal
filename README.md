# Bonus Task: Edge Removal from an MST

This project is my solution to the bonus task: Edge Removal from a Minimum Spanning Tree (MST), implemented in Java using Maven.

## What the program does

1. Builds a weighted, undirected graph.
2. Computes its MST using Kruskal's algorithm.
3. Displays the MST edges and their total weight.
4. Removes one chosen edge from the MST.
5. Shows the connected components after the edge removal.
6. Finds a replacement edge that reconnects the graph while keeping it a minimum spanning tree.
7. Prints the updated MST and its total weight.

The example graph consists of 8 vertices (0..7) and 9 edges.  
The MST is unique for the given weights.

## Project structure

```text
mst-edge-removal/
├── pom.xml  # Maven configuration file
├── README.md  # Project documentation
└── src
    ├── main
    │   └── java
    │       └── org
    │           └── example
    │               ├── Edge.java  # Class representing a graph edge
    │               ├── Graph.java  # Graph structure with vertices and edges
    │               ├── UnionFind.java  # Union-Find (disjoint-set) class for MST
    │               ├── KruskalMST.java  # Implements Kruskal's algorithm
    │               ├── MSTUpdater.java  # Handles edge removal and replacement in MST
    │               └── Main.java  # Main program entry point
    └── test
        └── java
            └── org
                └── example
                    └── MstEdgeRemovalTest.java  # JUnit tests for MST functionality
```

# How to run the project

### 1. Clone or download the repository:
   - To clone, use:
     ```bash
     git clone https://github.com/YOUR_USERNAME/mst-edge-removal.git
     cd mst-edge-removal
     ```

### 2. Build the project:
   ```bash
   mvn clean compile
  ```

### 3. Run the program

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

If you don't want to use the Maven exec plugin, you can also do:

```bash
mvn clean package
java -cp target/mst-edge-removal-1.0-SNAPSHOT.jar org.example.Main
```

### 4. Run tests

```bash
mvn test
```

## Output explanation

A sample run will print:

- The original MST edges and total weight.
- The edge being removed.
- The components (sets of vertices) after removing the edge.
- The replacement edge chosen from the original graph.
- The updated MST edges and total weight after reconnection.

This clearly demonstrates that:
- You can construct an MST.
- You can handle the removal of an MST edge.
- You can efficiently find a replacement edge using the cut property.
- The resulting tree is still a valid MST.
