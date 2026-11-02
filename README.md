Tic-Tac-Toe (Java)
A console-based two-player Tic-Tac-Toe game written in plain Java — no dependencies, no build tool, just javac and java.

Two players take turns entering a row and column on a 3×3 grid. The game detects wins across rows, columns and both diagonals, recognises a draw when the board fills up, keeps a running score, and lets you play as many rounds as you like without restarting the program. 

**Features:**
Two-player hot-seat play (X and O) on the same keyboard
Win detection for all 3 rows, 3 columns and both diagonals
Draw detection — the game ends cleanly when the board is full
Running score across rounds (X wins / O wins / draws)
Replay prompt after every game
Input validation that never crashes:
Out-of-range coordinates are rejected with a message
Non-numeric input is rejected instead of throwing an exception
Squares that are already taken are rejected, and the turn is not lost
Clean exit if the input stream ends mid-game (handy for scripted runs and piping)
Scanner closed via try-with-resources, so it is released even on an error path

**Requirements**
JDK 8 or newer (developed and tested on JDK 17)
A terminal

No external libraries. Nothing to install beyond the JDK.

**Getting started**
The source declares package Tictactoe;, so it must live in a folder named Tictactoe. From the parent directory:
# compile
javac Tictactoe/TicTacToe.java

# run
java Tictactoe.TicTacToe
Expected layout:

How to play
Run the program. The empty board is printed and X moves first.
Enter the row and column of your move, separated by a space:


Enter row and column (e.g. 1 2): 1 2
1,2 works too. Rows and columns are both numbered 0 to 2, with 0 0 being the top-left square.
If the square is free it is claimed and play passes to the other player. If it is out of range, not a number, or already taken, you are asked again.
The game ends when someone gets three in a row, or when all nine squares are filled (a draw).
Enter y at the prompt to play another round, or n to quit. The score is shown between rounds.
Sample session


   |   |   
---+---+---
   |   |   
---+---+---
   |   |   

Player X, it's your turn.
Enter row and column (e.g. 1 2): 0 0

 X |   |   
---+---+---
   |   |   
---+---+---
   |   |   

Player O, it's your turn.
Enter row and column (e.g. 1 2): 1 1
...
 X | X | X 
---+---+---
   | O | O 
---+---+---
   |   |   

Player X wins!
Score -> X: 1  O: 0  Draws: 0
Play again? (y/n): n
Thanks for playing!
How it works
The code is a single class with a few small, focused methods:




Method	Responsibility
main	Owns the replay loop and the running score; creates the Scanner
playGame	Plays one round: builds the board, alternates turns, returns the winner
printBoard	Draws the grid with | and --- separators
haveWon	Checks every row, column and both diagonals for a line of three
inBounds	Validates a coordinate pair against SIZE
readMove	Reads one line, parses row col, and re-prompts on bad input
readYesNo	Reads the replay answer
A few details worth pointing out:

Turn state is a plain char, flipped with player = (player == 'X') ? 'O' : 'X' after a valid move. An invalid move returns early via continue, so the turn is never skipped.
haveWon is driven by SIZE, not hardcoded indices, so the row and column loops are the same loop. &= is used instead of && so all three cells are always examined.
Draws are counted, not guessed. A moves counter increments on every valid placement; when it reaches SIZE * SIZE with no winner, the round is a draw. Without this check the game would loop forever once the board filled.
Input is read line by line rather than with nextInt(), which is what makes bad input recoverable — nextInt() leaves the offending token in the buffer and throws InputMismatchException.
readMove returns null at end of input, which propagates up as a QUIT result so the program exits instead of prompting forever.
Possible next steps
Single-player mode against a computer opponent — random moves first, then minimax for an unbeatable AI
Configurable board size (a 4×4 grid needs a different win rule, e.g. four in a row)
Score persistence between runs
Unit tests for haveWon and inBounds (both are public static and easy to test)
A GUI or a web front end
.
└── Tictactoe
    └── TicTacToe.java


