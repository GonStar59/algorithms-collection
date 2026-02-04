# Logistics Hamiltonian Path Optimizer

## Overview
This project implements a solver for the **Hamiltonian Path Problem** using a **Backtracking algorithm** on Directed Graphs. Developed as part of the Algorithms & Data Structures coursework at **Universidad Politécnica de Madrid (UPM)**.

The system constructs a graph from a cost matrix (adjacency matrix) and calculates a valid route that visits every node (location) exactly once without repetition. This type of algorithm is fundamental in logistics, route planning, and network analysis.

## Key Features
* **Graph Construction:** Dynamically converts Adjacency Matrices (`Integer[][]`) into `DirectedGraph` structures.
* **Hamiltonian Solver:** Implements a recursive Depth-First Search (DFS) with backtracking to find paths that visit all vertices `V` exactly once (`|V| == |Visited|`).
* **Cycle Detection:** efficiently manages a `Set<Vertex>` of visited nodes to prevent cycles and invalid paths.
* **Cost Calculation:** Includes a utility to compute the total weight (distance/cost) of the discovered path.