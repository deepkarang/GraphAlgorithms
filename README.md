# GraphAlgorithms

## Overview
This repository is meant for introducing beginners to the world of Graph Algorithms in Java.

It contains a simple implementation of the most commonly used graph traversal algorithms (and one more intelligent, not-so-commonly used algorithm) for your own personal learning and exploration. The code itself does the following:
- Randomly selects starting and ending points on a sample maze that contains obstructions.
- Runs the 3 algorithms (BFS, DFS, A*) on said topology and prints some high-level details about the traversals such as how many nodes were visited before discovering the target one, how long the final path found was, etc. 

<br/>

## Execution Instructions

```$ sh runAlgorithms.sh```

This compiles the source files into java bytecode and executes them on the JVM. You must have java installed on your machine to run this successfully.

You can run the code multiple times and you will always see A* outperform the others. Here are some reasons why...

<br/>

## Concepts 

The following GIFs are a nice visual demonstration of the common algorithms, BFS & DFS:

<br/>

BFS (***Breadth-First***-Search)

<a title="BFS" href="https://www.uniquesoftwaredev.com/wp-content/uploads/2019/12/bfs.gif"> <img width="256" alt="Depth-First-Search" src="https://www.uniquesoftwaredev.com/wp-content/uploads/2019/12/bfs.gif"></a>

<br/>

DFS (***Depth-First***-Search)

<a title="Mre, CC BY-SA 3.0 &lt;https://creativecommons.org/licenses/by-sa/3.0&gt;, via Wikimedia Commons" href="https://commons.wikimedia.org/wiki/File:Depth-First-Search.gif"><img width="256" alt="Depth-First-Search" src="https://upload.wikimedia.org/wikipedia/commons/7/7f/Depth-First-Search.gif"></a>

The third algorithm included in this package is the powerful Metaheuristic algorithm known as A* search. It consistently performs better than BFS on average, which performs better than DFS on average.

<br/>

## Result Analysis and Underlying Intuition

### DFS Sub-optimality
- We can see the DFS algorithm visits the smallest number of nodes to reach our end goal, but by doing so it restricts its search space and is unsuccessful in finding the most optimal path.

### BFS Standard performance
- The BFS algorithm does indeed find the most optimal path (level-order traversal), but it visits a larger number of nodes compared to our best performing algorithm, the A* search. 

### A* Edge
- This edge within the A* search lies within its use of heuristics to 'guide itself' towards paths leading to the end node that are likely shorter.
- The A* approach embeds additional intelligence into the algorithm by "peeking" over the topology to get an idea of where the end goal is, through some form of heuristic that it uses to guide its path. We see similar examples of this mechanism in nature by mammals. For example, flocking towards a stand of freshly cooked meat by following the smell when they're hungry. Or maybe when a group of travellers may cut through a forest in order to minimize the total trip time since they already have an idea of the topology and they know going around would be longer. The catch here is you need that additional heuristic or estimate as to how far different paths could be, at least, from the end goal.
