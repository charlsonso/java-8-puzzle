# Compilation
Using java and javac version "1.8.0_131" on Mac OSX 10.11.6
1. In the terminal, use the command  

``` javac AStar.java ExploredSet.java FrontierPriorityQueue.java Node.java Pair.java AStarProject.java```  

to compile your program

2. or use the class files in the bin directory to run your program

# Running the Program

1. In the directory containing your class files. Type  
``` java AStarProject ```  
to start the program

2. Option 1 will allow you to enter a 8-puzzle in the format "0 1 2 3 4 5 6 7 8"  
Option 2 will allow you to solve a random 8-puzzle
Option 3 will generate metrics for a 100 random puzzles. This will typically take a day to complete.

# Test
1. To run a basic test also compile AStarBasicTest.java  
``` javac AStarBasicTests.java```

2. Run the basic tests with
``` java AStarBasicTest ```  
and input a line from 100 Scrambled Puzzles.txt
