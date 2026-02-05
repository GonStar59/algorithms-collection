# Java Algorithms & Data Structures Collection

## Overview
This repository serves as a portfolio of advanced algorithm implementations and custom data structures developed during my Mathematics and Computer Science studies at **Universidad Politécnica de Madrid (UPM)**. 

## Modules

### 1. Hamiltonian Path
* **File:** `java/src/aed/delivery/Delivery.java`
* **Problem:** Hamiltonian Path Problem (NP-Complete).
* **Implementation:** Recursive **Backtracking** (DFS) on Directed Graphs.
* **Key Features:** * Efficient state management using HashSets for O(1) lookups.
  * Dynamic parsing of Adjacency Matrices.
  * Cycle detection.

### 2. Huffman Compression 
* **File:** `java/src/aed/huffman/Huffman.java`
* **Problem:** Lossless data compression.
* **Implementation:** Construction of optimal prefix codes using **Priority Queues** and Binary Trees.
* **Key Features:**
  * **StringBuilder** optimization for O(n) encoding complexity.
  * Recursive decoding logic traversing the Huffman Tree.

### 3. Treap 
* **File:** `java/src/aed/treap/Treap.java`
* **Problem:** Maintaining a balanced Binary Search Tree (BST) without complex deterministic rotations (like AVL/Red-Black).
* **Implementation:** A probabilistic data structure combining a BST and a Min-Heap.
* **Key Features:**
  * **O(log n)** expected complexity for search, insert, and delete operations.
  * **Custom Iterator:** Implemented memory-efficient in-order iterator.

### 4. Bisection Method
* **File:** `matlab\bisecc.m`
* Implementing an iterative algorithm with linear convergence to find the root of a continuous function.
  
### 5. Solving Linear Systems
* **File:** `matlab\resuelve.m`
* Using Gaussian Elimination with pivoting to solve linear systems. Pivoting minimizes floating-point errors and allows the solution of all matrices.

### 6. Newton's Polynomial Interpolation
* **File:** `matlab\newton.m`
* Calculating coefficients of the polynomial with the divided differences method and evaluating the function with an O(N) algorithm
  
### 7. Quadratic Spline
* **File:** `matlab\spline2.m`
* Using Quadratic Spline Interpolation to transform discrete datasets into $C^1$ functions
  
## Technologies
* **Language:** Java (JDK 22)
* **Dependencies:** UPM Academic Library (`es.upm.aedlib`)
