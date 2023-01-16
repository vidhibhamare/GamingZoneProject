# GamingZoneProject
PROJECT TITLE: ARCADE GAMING ZONE

PROBLEM STATEMENT: To make a gaming zone with three games by making
use of data structures.

DATA STRUCTURE USAGE:
1. Queues:
Used in snake game & Minesweeper Game
2. Binary Tree:
Used in Minesweeper game
3. Linked List:
Used in Treasure hunt game

BRIEF:

• Game 1: Snake Game
- Data Structure used: Queue
Queue is a linear data structure that is opened at both the ends, it is the collection of
ordered elements which implements First in First out (FIFO) order. Here, the additions
are made at one end, and the deletion happens at the other end
Queue operation used in the game:

1. Movement of the snake:
- Enqueue + Dequeue: In the game, the body of the snake is formed inside a
two-dimensional arraylist, which is similar to a grid; In order for the movement of the
long rectangle snake (made of small rectangles) in the grid (i.e arraylist), we have to
add one small rectangle at the rear and then remove a small rectangle from the front,
which is fundamentally the implementation of both Enqueue(adding an element at the
rear) and Dequeue(removing an element from the front) and the same time.

2. Growth of the Snake:
- Enqueue: When the snake eats one food, the tail of the snake increases by one
index, which is basically the implementation of Enqueue. This process keeps going
on, until the player dies by colliding the snake body to the wall of the frame or
colliding with its own body.

Time Complexities:
1. Enqueue: O(1)
2. Dequeue: O(1)

Game 2: Treasure Hunt Game
- Data Structure used: Linked List
A linked list is a linear data structure that includes a series of connected nodes. Here,
each node stores the data and the address of the next node. The elements in a linked
list are linked using pointers.
Here the linked list is used in storing clues and answers of a team. Hence, there are
two linked lists for two teams.The pointer moves to the next node only when the
previous node clue is solved otherwise the chance goes to the next team list. The two
linked lists merge at the last clue. The first team to reach the intersection of the list
wins the game clearly. The pointer of a team to the next node clue until the answer is
incorrect.
A toss is done at the start of the game to decide which list is started to traverse.
Time Complexities:
1. To add a clue to the next node: O(1)
2. Push: O(N)

Game 3: Minesweeper Game
- Data Structure used: Binary Tree and Queue
A binary tree is helpful for hierarchical traversals, which is useful in creating this
game by creating multiple 2-way paths. A queue uses the concept of First In First Out,
which is useful for creating the complete tree breadth wise. A complete Binary Tree of
height 10 is created by traversing breadthwise using the Queue.
This Binary Tree consists of each node having the details: whether mine is present at
that node or not, number of adjacent mines, that is total number of mines present in
the left and right child of that node and the link for the left and right child.
The random function is used to generate the number of adjacent mines and if the
parent has 1 adjacent mine then which child gets the mine.
The user, as per their choice of direction, moves forward until they come across a
mine. The root node does not contain any mine as it would stop the game before it
begins. The last (10th) level contains all the mines, indicating the end of the game.
The game can be played multiple times with different values generated randomly.
Time Complexities:
1. Creation: O(n)
2. Searching: O(h) [h is the height of the tree] considering the average case, tree with
height h has at least h elements therefore O(n)
