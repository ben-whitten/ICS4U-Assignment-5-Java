/*
* The Main program implements an application that
* can find a solution to a maze.
*
* @author  Ben Whitten
* @version 1.1
* @since   2020-1-17
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;  // Import the Scanner class

///////////////////////////////////////////////////////////////////////////////

class Main {

  public static String [] [] findSolution(String [] [] maze, int height,
                                          int length, int positionY,
                                          int positionX, int endY, int endX,
                                          String previousDirection) {
    
    maze [positionY] [positionX] = "\u001B[42m" + maze[positionY][positionX]
                                   + "\u001B[0m";
    
    int right = positionX + 1;
      
    if (right > length - 1) {
      right = length - 1;
    }
    
    int up = positionY - 1;

    if (up < 0) {
      up = 0;
    }

    int left = positionX - 1;
    
    if (left < 0) {
      left = 0;
    }
    
    int down = positionY + 1;
    
    if (down > height - 1) {
      down = height - 1;
    }

    if (maze[positionY][right].equals("G") || maze[up][positionX].equals("G")
        || maze[positionY][left].equals("G")
        || maze[down][positionX].equals("G")) {
      maze[endY][endX] = ("\u001B[42mG\u001B[0m");
      for (int counterY = 0; counterY < height; counterY++) {
        for (int counterX = 0; counterX < length; counterX++) {
          if (maze[counterY][counterX].equals("\u001B[41m.\u001B[0m")) {
            maze[counterY][counterX] = ".";
          }
        }
      }
      return maze;

    } else if (maze[positionY][right].equals(".") && right != positionX) {
      return findSolution(maze, height, length, positionY, right, endY, endX,
                          "left");

    } else if (maze[up][positionX].equals(".") && up != positionY) {
      return findSolution(maze, height, length, up, positionX, endY, endX,
                          "down");

    } else if (maze[positionY][left].equals(".") && left != positionX) {
      return findSolution(maze, height, length, positionY, left, endY, endX,
                          "right");

    } else if (maze[down][positionX].equals(".") && down != positionY) {
      return findSolution(maze, height, length, down, positionX, endY, endX,
                          "up");

    // Backtracking 1
    } else if (previousDirection.equals("right")) {
      if (maze[up][positionX].equals(".") || maze[positionY][left].equals(".")
          || maze[down][positionX].equals(".")) {
        return findSolution(maze, height, length, positionY, right, endY, endX,
                            "");
      } else {
        maze[positionY][positionX] = ("\u001B[41m.\u001B[0m");
        return findSolution(maze, height, length, positionY, right, endY, endX,
                            "");
      }

    } else if (previousDirection.equals("up")) {
      if (maze[positionY][right].equals(".")
          || maze[down][positionX].equals(".")
          || maze[positionY][left].equals(".")) {
        return findSolution(maze, height, length, up, positionX, endY, endX,
                            "");
      } else {
        maze[positionY][positionX] = ("\u001B[41m.\u001B[0m");
        return findSolution(maze, height, length, up, positionX, endY, endX,
                            "");
      }

    } else if (previousDirection.equals("left")) {
      if (maze[up][positionX].equals(".") || maze[positionY][right].equals(".")
          || maze[down][positionX].equals(".")) {
        return findSolution(maze, height, length, positionY, left, endY, endX,
                            "");
      } else {
        maze[positionY][positionX] = ("\u001B[41m.\u001B[0m");
        return findSolution(maze, height, length, positionY, left, endY, endX,
                            "");
      }
        
    } else if (previousDirection.equals("down")) {
      if (maze[positionY][right].equals(".") || maze[up][positionX].equals(".")
          || maze[positionY][left].equals(".")) {
        return findSolution(maze, height, length, down, positionX, endY, endX,
                            "");
      } else {
        maze[positionY][positionX] = ("\u001B[41m.\u001B[0m");
        return findSolution(maze, height, length, down, positionX, endY, endX,
                            "");
      }

    // Backtracking 2
    } else if (maze[positionY][right].equals("\u001B[42m.\u001B[0m")
               && right != positionX) {
      if (maze[up][positionX].equals(".") || maze[positionY][left].equals(".")
          || maze[down][positionX].equals(".")) {
        return findSolution(maze, height, length, positionY, right, endY, endX,
                            "");
      } else {
        maze[positionY][positionX] = ("\u001B[41m.\u001B[0m");
        return findSolution(maze, height, length, positionY, right, endY, endX,
                            "");
      }

    } else if (maze[up][positionX].equals("\u001B[42m.\u001B[0m")
               && up != positionY) {
      if (maze[positionY][right].equals(".")
          || maze[down][positionX].equals(".")
          || maze[positionY][left].equals(".")) {
        return findSolution(maze, height, length, up, positionX, endY, endX,
                            "");
      } else {
        maze[positionY][positionX] = ("\u001B[41m.\u001B[0m");
        return findSolution(maze, height, length, up, positionX, endY, endX,
                            "");
      }

    } else if (maze[positionY][left].equals("\u001B[42m.\u001B[0m")
               && left != positionX) {
      if (maze[up][positionX].equals(".") || maze[positionY][right].equals(".")
          || maze[down][positionX].equals(".")) {
        return findSolution(maze, height, length, positionY, left, endY, endX,
                            "");
      } else {
        maze[positionY][positionX] = ("\u001B[41m.\u001B[0m");
        return findSolution(maze, height, length, positionY, left, endY, endX,
                            "");
      }
      
    } else if (maze[down][positionX].equals("\u001B[42m.\u001B[0m")
               && down != positionY) {
      if (maze[positionY][right].equals(".") || maze[up][positionX].equals(".")
          || maze[positionY][left].equals(".")) {
        return findSolution(maze, height, length, down, positionX, endY, endX,
                            "");
      } else {
        maze[positionY][positionX] = ("\u001B[41m.\u001B[0m");
        return findSolution(maze, height, length, down, positionX, endY, endX,
                            "");
      }
    }
    return maze;
  }
    
  /////////////////////////////////////////////////////////////////////////////
    
  public static void main(String [] args) throws FileNotFoundException {

    Scanner scanName = new Scanner(System.in);
    
    System.out.println("Input the name of the file containing the maze:");
    String fileName = scanName.nextLine();
    
    //creating the maze as an object.
    File text = new File(fileName);

    //Creating Scanner and scanning the maze.
    Scanner scanMaze = new Scanner(text);
    Scanner scanMazeAgain = new Scanner(text);
     
    //Reading each line of file using Scanner class to print out the maze.
    System.out.println("      - Maze -      ");
    System.out.println("====================");
    String line;
    int height = 0;
    int length = 0;
    while (scanMaze.hasNextLine()) {
      line = scanMaze.nextLine();
      System.out.println(line);
      length = line.length();
      height++;
    }
    
    int counterY;
    int counterX;

    //Adding the maze to a list.
    String [] [] maze = new String [height] [length];
    for (counterY = 0; counterY < height; counterY++) {
      line = scanMazeAgain.nextLine();
      for (counterX = 0; counterX < length; counterX++) {
        maze [counterY] [counterX] = "" + line.charAt(counterX);
      }
    }
    
    System.out.println("");

    //Finding the solution.
    ///////////////////////////////////////////////////////////////////////////
    System.out.println("");
    System.out.println("    - Solution -    ");
    System.out.println("====================");

    int startY = -1;
    int startX = -1;
    int endY = -1;
    int endX = -1;
    
    //Finding the start and end of the maze.
    for (counterY = 0; counterY < height; counterY++) {
      for (counterX = 0; counterX < length; counterX++) {
        if (maze[counterY][counterX].equals("S")) {
          startY = counterY;
          startX = counterX;
        } else if (maze[counterY][counterX].equals("G")) {
          endY = counterY;
          endX = counterX;
        }
      }
    }

    //Finding the solution
    String [] [] solution = findSolution(maze, height, length, startY, startX,
                           endY, endX, "");
    
    //Printing the solution
    for (counterY = 0; counterY < height; counterY++) {
      System.out.println("");
      for (counterX = 0; counterX < length; counterX++) {
        System.out.print(solution[counterY][counterX]);
      }
    }
  }
}
