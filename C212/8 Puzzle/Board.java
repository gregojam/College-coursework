/*************************************************************************
 * Name: James Gregory        
 * 
 * Lab 7 and Homework 5:  N-puzzle problem  
 * (no submission for lab 7, just submit to homeowrk 5 box) 
 * 
 * Released:  2/21/16
 * Due:       2/29/16
 * 
 * Add as many private helper functions as you want
 *
 *************************************************************************/

import static java.lang.Math.*;
import java.util.Arrays;
public class Board {
    
    // 2-d array to represent puzzle
    private final int[][] board;
    
    private final int N; // Depth of array
    
    /* I, J are indices in the board where the blank square is located.  
     * Saving this will be helpful for neighbors method 
     * blank square is represented with the value 0
     */
    private int I;
    private int J;
    
    // initialize NxN Puzzle Board 
    public Board(int[][] blocks) {
      N = blocks.length;
      board = new int[N][N];
      
      /* TO-DO
       * Copy blocks into board instance field 
       * Save i,j index where the blank square is
       */
      
      for(int i = 0; i < blocks.length; i++){
        for(int j = 0; j < blocks.length; j++){
          board[i][j] = blocks[i][j];
          if(blocks[i][j] == 0){
            I = i;
            J = j;
          }
          else;
          //Do Nothing
        }
      }
    }
    
    // return board dimension
    public int dimension() {
        return N;
    }
    
    // return # of blocks out of position
    public int hamming() {
      int shouldBe = 1;
      int wrong = -1;//accounts for blank square
      for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
          if(board[i][j] != shouldBe)
            wrong++;
          else;
          //Do Nothing
          shouldBe++;
        }
      }
      return wrong;
    }
    
    // return Manhattan distances between blocks and goal
    public int manhattan() {
      int sum = 0;
      for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
          if(board[i][j] != 0){
            sum += Math.abs(i - (board[i][j] - 1) / N);
            sum += Math.abs(j - (board[i][j] - 1) % N);
          }
          else;
          //Do Nothing
        }
      }
      return sum;
    }
    
    // is this Board the goal board?
    public boolean isGoal() {
      if(hamming() == 0)
        return true;
      else
        return false;
    }
    
    /* swaps any two numbers next to each other 
     * - itterate through the board, and swap first two numbers next to each other 
     *   (not including the blank square) 
     */ 
    public Board twin() {
        int[][] twin = new int[N][];
        for(int i = 0; i < N; i++)
            twin[i] = board[i].clone();
        boolean keepGoing = true;
        int posn = 0;
        int i = 0;
        int j = 0;
        int temp = 0;
        if(twin[0][0] != 0){
          if(twin[0][1] != 0){
            temp = twin[0][0];
            twin[0][0] = twin[0][1];
            twin[0][1] = temp;
          }
          else if(twin[1][0] != 0){
            temp = twin[0][0];
            twin[0][0] = twin[1][0];
            twin[1][0] = temp;
          }
          else;
          //Do Nothing
        }
        else{
          temp = twin[0][1];
          twin[0][1] = twin[0][2];
          twin[0][2] = temp;
        }
        Board alpha = new Board(twin);
        return alpha;
    }
    
    // returns board as a string
    public String toString() {
      String result = "\n";
      for(int i = 0; i < N; i++){
        result += "[";
        for(int j = 0; j < N; j++){
          result += board[i][j];
          if(j != N -1)
            result += " ";
        }
        result += "]\n";
      }
      return result;
    }
    
    // Hint:  if two boards have equal strings then they are equal
    public boolean equals(Object other) {
      if(this.toString().equals(other.toString()))
        return true;
      else
        return false;
    }
    
    /* all neighboring boards – all the possible moves that can be made from the current board
     * Can only swap numbers (blocks) with the blank square
     *  - From the index of the blank square, swap with all blocks above, below, left, and right 
     *    (that are in bounds)
     *  - Create a new board for each of these and add them all to the stack 
     */
    
    private Board tryMove(int x, int y){
      int[][] child = new int[N][];
        for(int i = 0; i < N; i++)
          child[i] = board[i].clone();
      try{
          int temp = child[I][J];
          child[I][J] = child[I+x][J+y];
          child[I+x][J+y] = temp;
          Board b = new Board(child);
          return b;
        }catch(IndexOutOfBoundsException e){return null;}
    }
        
    public Stack<Board> neighbors() {
        Stack<Board> stack = new Stack<>();

        if(tryMove(1, 0) != null)
          stack.push(tryMove(1, 0));
        if(tryMove(0, 1) != null)
          stack.push(tryMove(0, 1));
        if(tryMove(-1, 0) != null)
          stack.push(tryMove(-1, 0));
        if(tryMove(0, -1) != null)
          stack.push(tryMove(0, -1));
        return stack;
    }
    
    // test client
    public static void main(String[] args) {
      int[][] a = {{1,2,3},{4,5,6},{7,8,0}};
      int[][] b = {{8,1,3},{4,0,2},{7,6,5}};
      Board A = new Board(a);
      Board B = new Board(b);
      
      System.out.println("\nTest Block" + "\n====================" +
                         "\nT1 " + A.isGoal() +
                         "\nT2 " + !B.isGoal() +
                         "\nT3 " + (A.hamming() == 0) +
                         "\nT4 " + (A.manhattan() == 0) +
                         "\nT5 " + (B.hamming() == 5) +
                         "\nT6 " + (B.manhattan() == 10) +
                         "\nT7 " + A.equals(A) +
                         "\nT8 " + !A.equals(B) +
                         "\nT9 " + A.toString().equals("\n" +
                                                       "[1 2 3]\n" +
                                                       "[4 5 6]\n" +
                                                       "[7 8 0]\n") +
                         "\nT10 Twin\n" + A.toString() + A.twin() +
                         "\nT11 Neighbors\n" + A.toString() + A.neighbors() +
                         "\n====================\n\n" );
    }
}