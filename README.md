# Java Algorithms & Data Structures Collection

## Overview
This repository serves as a portfolio of advanced algorithm implementations and custom data structures developed during my Computer Engineering studies at **Universidad Politécnica de Madrid (UPM)**. 

The focus of this collection is on **algorithmic complexity**, **memory efficiency**, and **clean architecture**.

## Modules

### 1. Hamiltonian Path
* **File:** `java/src/aed/delivery/Delivery.java`
* **Problem:** Hamiltonian Path Problem (NP-Complete).
* **Implementation:** Recursive **Backtracking** (DFS) on Directed Graphs.
* **Key Features:** * Efficient state management using HashSets for O(1) lookups.
  * Dynamic parsing of Adjacency Matrices.
  * Cycle detection and pruning optimization.

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
  * **Custom Iterator:** Implemented memory eficient in-order iterator.

## Technologies
* **Language:** Java (JDK 22)
* **Dependencies:** UPM Academic Library (`es.upm.aedlib`)
