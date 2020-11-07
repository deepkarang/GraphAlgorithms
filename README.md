# GraphAlgorithms

## Overview
This repository is meant for introducing beginners to the world of Graph Algorithms in Java.

It contains a simple implementation of the most commonly used graph traversal algorithms BFS & DFS for your own personal learning and exploration. Here is a concise depiction of the 2 algorithms:

<br/>

BFS (***Breadth-First***-Search)

<a title="BFS" href="https://www.uniquesoftwaredev.com/wp-content/uploads/2019/12/bfs.gif"> <img width="256" alt="Depth-First-Search" src="https://www.uniquesoftwaredev.com/wp-content/uploads/2019/12/bfs.gif"></a>

<br/><br/>

DFS (***Depth-First***-Search)

<a title="Mre, CC BY-SA 3.0 &lt;https://creativecommons.org/licenses/by-sa/3.0&gt;, via Wikimedia Commons" href="https://commons.wikimedia.org/wiki/File:Depth-First-Search.gif"><img width="256" alt="Depth-First-Search" src="https://upload.wikimedia.org/wikipedia/commons/7/7f/Depth-First-Search.gif"></a>

For the people that want to take it a step further, it also contains an implementation of the famous Metaheuristic algorithm, A* search. It is known to perform better than BFS on average, which performs better than DFS on average.

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
