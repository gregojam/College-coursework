////////////////////
//
//C212 Spring 2016
//Homework2
//
//Author: James Gregory
//Last Modified: 2/5/16
//
////////////////////

import java.util.Scanner;

class DieSimulation{
  
  private Die die;
  private Counter one;
  private Counter two;
  private Counter three;
  private Counter four;
  private Counter five;
  private Counter six;
  
  public DieSimulation(){
    die = new Die();
    one = new Counter("one");
    two = new Counter("two");
    three = new Counter("three");
    four = new Counter("four");
    five = new Counter("five");
    six = new Counter("six");
  }
  
  public String run(){
    Scanner in = new Scanner(System.in);
    int n = 0;
    int roll = 0;
    String result = "";
    Counter[] array = new Counter[6]; // last time typing all of these
    array[0] = one;
    array[1] = two;
    array[2] = three;
    array[3] = four;
    array[4] = five;
    array[5] = six;
    
    boolean keepGoing = true;
    while(keepGoing){
      keepGoing = false;
      System.out.print("How many times would you like to roll the dice? ");
      if(in.hasNextInt())
        n = in.nextInt();
      else{
        System.out.println("Invalid Input");
        in.nextLine();
        keepGoing = true;
      }
    }
    
    for(int i = 0; i < n; i++){
      roll = die.roll();
      for(int j = 0; j < array.length; j++){
        if(roll == j + 1)
          array[j].increment();
        else; // Do Nothing! :-D
      }
    }
    
    for(int i = 0; i < array.length; i++)
      result += array[i].toString() + "\n";
    
    return result;
  }

  public static void main(String[] args){
    DieSimulation goTime = new DieSimulation();
    System.out.println(goTime.run());
  }
}
      
      