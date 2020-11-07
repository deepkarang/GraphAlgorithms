# GraphAlgorithms

## Overview
This repository is meant for introducing beginners to the world of Graph Algorithms in Java.

It contains a simple implementation of the most commonly used graph traversal algorithms BFS (Breadth-First-Search) & DFS (Depth-First-Search) for your own personal learning and exploration. 

For the beginners that want to take it a step further, it also contains an implementation of the famous Metaheuristic algorithm, A* search. It is known to perform better than BFS on average, which performs better than DFS on average.

## Execution Instructions

First compile the class by running:

```javac GraphAlgorithms.java```

Then execute the Java Bytecode with:

```java GraphAlgorithms```

## Result Analysis and Underlying Intuition

### DFS Sub-optimality
- We can see the DFS algorithm visits the smallest number of nodes to reach our end goal, but by doing so it restricts its search space and is unsuccessful in finding the most optimal path.

### A* Edge
- The BFS algorithm does indeed find the most optimal path (level-order traversal), but it visits a larger number of nodes compared to our best performing algorithm, the A* search. 
- This edge within the A* search lies within its use of heuristics to 'guide itself' towards paths leading to the end node that are likely shorter.
