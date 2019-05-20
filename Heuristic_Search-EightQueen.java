//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           EightQueens
//
// Author:          Mudit Joshi
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.util.*;

/**
 * 
 * Calls the State class with all appropriate values
 * 
 * print output based on option (flag)
 * feel free to add/remove/modify methods/fields
 
 * 
 * where F LAG is an integer that specifies the output of the program 
 * seed is a seed of a
 * random number generator (only required in part c and e; discarded otherwise).
 *  board is a string of length 8
 * that specifies the board as an array where the index is the column 
 * and the value is the row.
 * 
 * Each position in
 * the string takes values in integer 0-7. To illustrate, the board 
 * below is represented as 13572064. Note that
 * the board is a solution to the eight queens puzzle. That is, no 
 * two queens share the same row, column, or
 * diagonal.
 * 
 *
 * The eight queens puzzle is the problem of placing eight chess queens
 *  on an 8 × 8 chessboard in such
 * a way that no two queens attack each other. In other words,
 *  a solution requires that no two queens
 * share the same row, column, or diagonal.
 * 
 * 
 * @author Mudit Joshi
 * 
 * 
 *
 */
public class EightQueen {
	
    public static void main(String args[]) {
        if (args.length != 2 && args.length != 3) {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }

        int flag = Integer.valueOf(args[0]);
        int option = flag / 100;
        int iteration = flag % 100;
        char[] board = new char[8];
        int seed = -1;
        int board_index = -1;

        if (args.length == 2 && (option == 1 || option == 2 || option == 4)) {
            board_index = 1;
        } else if (args.length == 3 && (option == 3 || option == 5)) {
            seed = Integer.valueOf(args[1]);
            board_index = 2;
        } else {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }

        if (board_index == -1) return;
        for (int i = 0; i < 8; i++) {
            board[i] = args[board_index].charAt(i);
            int pos = board[i] - '0';
            if (pos < 0 || pos > 7) {
                System.out.println("Invalid input: queen position(s)");
                return;
            }
        }

        State initialstate = new State(board);
        initialstate.printStateOfQueen(option, iteration, seed);
    }
}

/**
 * print output based on option (flag)
 * feel free to add/remove/modify methods/fields
 * 
 * The eight queens puzzle is the problem of placing eight chess queens
 *  on an 8 × 8 chessboard in such
 * a way that no two queens attack each other. In other words,
 *  a solution requires that no two queens
 * share the same row, column, or diagonal.
 * 
 * where F LAG is an integer that specifies the output of the program 
 * seed is a seed of a
 * random number generator (only required in part c and e; discarded otherwise).
 *  board is a string of length 8
 * that specifies the board as an array where the index is the column 
 * and the value is the row.
 * 
 * Each position in
 * the string takes values in integer 0-7. To illustrate, the board 
 * below is represented as 13572064. Note that
 * the board is a solution to the eight queens puzzle. That is, no 
 * two queens share the same row, column, or
 * diagonal.
 *
 * @author Mudit Joshi
 * 
 * 
 *
 */ 
class State {     
	
	// Eight Queens Boards
    char[] board;  
    
    /** 
     * 
     * Constructor
     * 
     * Declares and Initializes Board Array 
     * 
     * @param BoardArray Array of char making the board
     */
    
    public State(char[] BoardArray) {
        this.board = Arrays.copyOf(BoardArray, BoardArray.length);
    }
    
    /**
     * 
     * Put Queen on the following looping values
     * 
     * The eight queens puzzle is the problem of placing eight chess queens
     *  on an 8 × 8 chessboard in such
     * a way that no two queens attack each other. In other words,
     *  a solution requires that no two queens
     * share the same row, column, or diagonal.
     * 
     * @param Queenboard EightQueen Board
     * @return Count Queen Numbers
     * 
     */
    private int QueenNumberCounter(char[] Queenboard) {
    	int Queennumber = 0;
    	for(int boardRow = 0; boardRow< Queenboard.length;boardRow++) {
    		for(int i = boardRow + 1; i < Queenboard.length;i ++) {
    			if(Queenboard[boardRow] == Queenboard[i]) {
    				Queennumber++;
    			}
    			else if(Queenboard[boardRow] == Queenboard[i] + i - boardRow) {
    				Queennumber++;
    			}
    			else if(Queenboard[boardRow] == Queenboard[i] -(i - boardRow)) {
    				Queennumber++;
    			}
    		}
    	}
    	return Queennumber;
    }

    /**
     * 
     * Put Queen on the following looping values
     * 
     * The eight queens puzzle is the problem of placing eight chess queens
     *  on an 8 × 8 chessboard in such
     * a way that no two queens attack each other. In other words,
     *  a solution requires that no two queens
     * share the same row, column, or diagonal.
     * 
     * @param QueenBoard EightQueen Board
     * @param lowCost lowest Queen Count
     * @return Succesive states of Queen
     * 
     */
    private ArrayList<State> lowestQueenNumberCounter(char[] QueenBoard, 
    		int lowCost) { 
    	int potQueenNumber = 100;
    	boolean checkForQueen = false;
    	ArrayList<State> queenSuccessState = new ArrayList<State>();
    	char[] newQueenBoard = Arrays.copyOf(QueenBoard, QueenBoard.length);
    	for(int boardRow = 0; boardRow < QueenBoard.length;boardRow++) {
    		for(int j = 0; j < QueenBoard.length;j++) {
    			newQueenBoard[boardRow] = Integer.toString(j).toCharArray()[0];
    			
    			potQueenNumber = QueenNumberCounter(newQueenBoard);
    			if(potQueenNumber == lowCost) {
    				
    				State nextSuccessState = new State(newQueenBoard);
    				for(int i = 0; i < queenSuccessState.size();i++) {
    					if(Arrays.equals(nextSuccessState.board,
    							queenSuccessState.get(i).board)) {
    						checkForQueen = true;
        				}
    				}
    				if(!checkForQueen) {
    					queenSuccessState.add(nextSuccessState);
    				}
    				checkForQueen = false;
    			}
    		}
    		
    		newQueenBoard = Arrays.copyOf(QueenBoard, QueenBoard.length);
    	}
    	for(int i = 0;i<queenSuccessState.size();i++) {
    		if( Arrays.equals(queenSuccessState.get(i).board,QueenBoard)){
    			queenSuccessState.remove(i);
    		}
    	}
    	
    	return queenSuccessState;
    }
        
    /**
     * 
     * Put Queen on the following looping values
     * 
     *  The eight queens puzzle is the problem of placing eight chess queens
     *  on an 8 × 8 chessboard in such
     * a way that no two queens attack each other. In other words,
     *  a solution requires that no two queens
     * share the same row, column, or diagonal.
     * 
     * @param QueenBoard EightQueen Board
     * @param Cost  Queen Count
     * @return Succesive states of Queen
     * 
     */
    private int lowestQueenNumberCounter2(char[] QueenBoard, int QueenNumber) {
    	int potQueenNumber = 100;
    	char[] newQueenBoard = Arrays.copyOf(QueenBoard, QueenBoard.length);
    	for(int boardRow = 0; boardRow < QueenBoard.length;boardRow++) {
    		for(int j = 0; j < QueenBoard.length;j++) {
    			newQueenBoard[boardRow] = Integer.toString(j).toCharArray()[0];
    			potQueenNumber = QueenNumberCounter(newQueenBoard);
    			if(potQueenNumber < QueenNumber) {
    				QueenNumber = potQueenNumber;
    			}
    		}
    		newQueenBoard = Arrays.copyOf(QueenBoard, QueenBoard.length);
    	}
    	return QueenNumber;
    }

    /**
     * 
     * Put Queen on the following looping values
     * 
     *  The eight queens puzzle is the problem of placing eight chess queens
     *  on an 8 × 8 chessboard in such
     * a way that no two queens attack each other. In other words,
     *  a solution requires that no two queens
     * share the same row, column, or diagonal.
     * 
     * @param QueenBoard EightQueen Board
     * @param lowCost lowest Queen Count
     * 
     */
    private void printBoardString(char[] QueenBoard,
    		int loopingnumb, int lowestQueenNumber) {
    	String boardOfString = new String(QueenBoard);
    	System.out.print(loopingnumb + ":" + boardOfString + " " 
    	+ lowestQueenNumber);
    	System.out.println();
    } 
    
    /**
     * 
     * where F LAG is an integer that specifies the output of the program 
     * seed is a seed of a
     * random number generator (only required in part c and e; 
     * discarded otherwise).
     *  board is a string of length 8
     * that specifies the board as an array where the index is the column 
     * and the value is the row.
     * 
     * @param flagtypes int type definer
     * @param loopnumber int looper
     * @param queenSeed int random seed
     * 
     */
    public void printStateOfQueen(int flagtypes, int loopnumber, int queenSeed) {
    	
    	if(flagtypes == 1) {
    		System.out.println(QueenNumberCounter(board));
    	}
    	else if(flagtypes == 2) {
    		if(QueenNumberCounter(board) ==0) {
    			return;
    		}      
    		 
    		int queenNumber = QueenNumberCounter(board);
    		
    		int lowestQueenNumber = lowestQueenNumberCounter2(board,queenNumber);
    	
    		ArrayList<State> queenSuccessState = 
    				lowestQueenNumberCounter(board,lowestQueenNumber);
    		
    		for(int i = 0; i <queenSuccessState.size();i++) {
    			if(queenSuccessState.get(i) != null) {
    				System.out.println(queenSuccessState.get(i).board);
    			}
    		}
    		System.out.println(lowestQueenNumber);
    	} 
    	else if(flagtypes == 3) {  
    		ArrayList<State> queenState3 = new ArrayList<State>();
    		int queenNumber3 = QueenNumberCounter(board); 
    		if(queenNumber3 == 0) {
    			printBoardString(board,0,queenNumber3);
				System.out.println("Solved");
				return;
			}
    		else { 
    			State initQueenState3 = new State(board);
    			queenState3.add(initQueenState3);
    		}  
    		   
    		int lowestQueenNumber3 =
    				lowestQueenNumberCounter2(board,queenNumber3);
    		int loopingnumber = 0; 
    		
    		ArrayList<State> queenSuccessState3 =
    				lowestQueenNumberCounter(board,lowestQueenNumber3);
    		
    		Random randomNumber = new Random();
    		if (queenSeed != -1) randomNumber.setSeed(queenSeed);
    		int Random = randomNumber.nextInt(queenSuccessState3.size());
    		State changeQueenState = queenSuccessState3.get(Random);
    		
    		while(loopingnumber < loopnumber ) {
    			queenState3.add(changeQueenState);
    			if(lowestQueenNumber3 ==0) { 
    				break;
    			}
    		
    			lowestQueenNumber3 = 
    					lowestQueenNumberCounter2(changeQueenState.board,
    							lowestQueenNumber3);
    			queenSuccessState3 =
    					lowestQueenNumberCounter(changeQueenState.board,
    							lowestQueenNumber3);
    			if(queenSuccessState3.size() != 0) {
    				Random = randomNumber.nextInt(queenSuccessState3.size());
        			changeQueenState = queenSuccessState3.get(Random);
    			}
        		loopingnumber++;
        		if(lowestQueenNumber3 == 0) {
    				queenState3.add(changeQueenState);
    				break;
    			}
    	}
    		for(int i = 0; i <queenState3.size();i++) {
				printBoardString(queenState3.get(i).board,i,
						QueenNumberCounter(queenState3.get(i).board));
			}
    		if(lowestQueenNumber3 == 0) {
				System.out.println("Solved");
			} 
    }   
    	else if(flagtypes == 4) {  
    		ArrayList<State> queenState4 = new ArrayList<State>();
    		int queenNumber4 = QueenNumberCounter(board);
    		int loopingnumber4 = 0;
    		boolean MaxFound = false;
    		State initQueenState4 = new State(board);
    		if(queenNumber4 == 0) {
    			printBoardString(board,0,queenNumber4);
				System.out.println("Solved");
				return;
			}
    		else {
    			queenState4.add(initQueenState4);
    		}   
    		
    		State changeQueenState4 = 
    				nextStateOfQueen(initQueenState4,queenNumber4);
    		int nextQueen4 = QueenNumberCounter(changeQueenState4.board);
    		
    		while(nextQueen4 <= queenNumber4 && loopingnumber4 < loopnumber) {
    			changeQueenState4 = 
    					nextStateOfQueen(changeQueenState4,queenNumber4);
    			if(changeQueenState4 != null) {
    				queenNumber4 = QueenNumberCounter(changeQueenState4.board);
    				nextQueen4 = QueenNumberCounter(changeQueenState4.board);
    				queenState4.add(changeQueenState4);
    			}
    			else {
    				MaxFound = true;
    				break;
    			}
    			loopingnumber4++;
    		}
    		
    		for(int i = 0; i < queenState4.size();i++) {
    			printBoardString(queenState4.get(i).board,i,
    					QueenNumberCounter(queenState4.get(i).board));
    		}
    		if(queenNumber4 == 0) {
				System.out.println("Solved");
			}
    		else if(MaxFound){
    			System.out.println("Local optimum");
    		}   
    	}  
    	else if(flagtypes == 5){  
    		
    		double RateAnneal = 0.95;
    		double Temp = 100;
    		int numberDeltaE = 0;
    		int loopingnumber5 = 0;  
    		
    		State initQueenState5 = new State(board);
    		
    		int queenNumber5 = QueenNumberCounter(initQueenState5.board);
    		int nextQueenCount5 =  100;
    		
    		ArrayList<State> queenSuccessState5 = new ArrayList<State>();
    		
    		int newQueenCost = QueenNumberCounter(board);
    		if(newQueenCost == 0) {
    			printBoardString(board,0,newQueenCost);
				System.out.println("Solved");
				return;
		   }
    		else {
    			queenSuccessState5.add(initQueenState5);
    		} 
    		
    		Random randomNumber5 = new Random();
    		if (queenSeed != -1) randomNumber5.setSeed(queenSeed);
    		
    		
    		while(loopingnumber5 < loopnumber) {
    			Temp = Temp - RateAnneal;
    			if(Temp <= 0) {
    				break;
    			}   
    			
        		int indexPointer = randomNumber5.nextInt(7);
        		int pointValue = randomNumber5.nextInt(7);
        		double Pointerprob = randomNumber5.nextDouble();
        	
        		State newQueenSuccessState5 = new State(initQueenState5.board);
        		newQueenSuccessState5.board[indexPointer] = 
        				Integer.toString(pointValue).toCharArray()[0];
        		//System.out.println(newQueenSuccessState5.board);
        		nextQueenCount5 =
        				QueenNumberCounter(newQueenSuccessState5.board);
    			numberDeltaE = nextQueenCount5 - queenNumber5;
    			
    			if(numberDeltaE <= 0) {
    				queenSuccessState5.add(newQueenSuccessState5);
    				initQueenState5 = newQueenSuccessState5;
    				queenNumber5 = nextQueenCount5;
    			}
    			else {     
    				
    				double boardUpperBoundary = Math.exp(numberDeltaE);
    				double boardLowerBoundary = 1;
    				double newPointerValue = Math.exp(numberDeltaE/Temp);
    				double scaledPointerValue = 
    						1 - (boardUpperBoundary- newPointerValue)/
    						(boardUpperBoundary - boardLowerBoundary);
    				if(Pointerprob > scaledPointerValue) {
    					queenSuccessState5.add(newQueenSuccessState5);
        				initQueenState5 = newQueenSuccessState5;
        				queenNumber5 = nextQueenCount5;
    				} 
    				else {
    					queenSuccessState5.add(initQueenState5);
    				}
    			}
    			loopingnumber5++;
    		}
    		for(int i = 0; i < queenSuccessState5.size();i++) {
    			printBoardString(queenSuccessState5.get(i).board,i,
    					QueenNumberCounter(queenSuccessState5.get(i).board));
    		} 

    		if(queenNumber5 == 0) {
				System.out.println("Solved");
			}
    	}
    }
  
    /**
     * 
     * Count the number of queen and prints the next state of the queen
     * 
     *  The eight queens puzzle is the problem of placing eight chess queens
     *  on an 8 × 8 chessboard in such
     * a way that no two queens attack each other. In other words,
     *  a solution requires that no two queens
     * share the same row, column, or diagonal.
     * 
     * @param QueenState Queen State 
     * @param QueenCounter Queen current number
     * @return Queen Board
     * 
     */ 
    private State nextStateOfQueen(State QueenState, int QueenCounter) {
    	int QueenNumber = 100; 
    	char[] QueenBoard = Arrays.copyOf(QueenState.board, QueenState.board.length);
    	for(int boardRow = 0; boardRow < QueenState.board.length;boardRow++) {
    		for(int j = 0; j < QueenState.board.length;j++) {
    			QueenBoard[boardRow] = Integer.toString(j).toCharArray()[0];
    			QueenNumber = QueenNumberCounter(QueenBoard);
    			if(QueenNumber<QueenCounter) {
    				State newQueenState = new State(QueenBoard);
    				return newQueenState;
    			}
    		}
    		QueenBoard = Arrays.copyOf(QueenState.board, QueenState.board.length);
    	}
    	return null;
    }
    
}
