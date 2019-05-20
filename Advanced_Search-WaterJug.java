//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           WaterJug
//
// Author:          Mudit Joshi
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.*;

/**
 *  
 *  Uniformed Search Class
 *  
 * @author Mudit Joshi
 *
 */
class UninformedSearch {
	 
    /**
     * 
     * Calls other methods
     * 
     *
     * @param option int for Flag
     * @param curr_state int representing state
     * @param depth int representing depth
     */
    public static void recursivecall(State curr_state, int option, int depth) {
     
    // Gey the successors
    if (option == 1) {
        State[] nextState = curr_state.getNextState();
        for (int i = 0; i < nextState.length; i++) {
        System.out.println(nextState[i].curr_jug1 + "" + 
        nextState[i].curr_jug2);
        }
        }
     
    // Get the successors, show goal states
    if (option == 2) {
    State[] nextstate2 = curr_state.getNextState();
    for (int i = 0; i < nextstate2.length; i++) {
    System.out.println(nextstate2[i].curr_jug1 + "" + 
    nextstate2[i].curr_jug2 + " " + 
    nextstate2[i].FindGoalState());
    }
    }
    // Do a BFS
    if (option == 3) {
    breadthfirstsearch(curr_state);
    }
    // Do a DFS
    if (option == 4) {
    depthfirstsearch(curr_state);
    }
    // Do an IDDFS
    if (option == 5) {
     iterativedeepeningsearch(curr_state, depth);
    }
    }
    
   
    /**
     * 
     * run iterative deep  search algorithm
     * 
     *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
     * @param curr_state int representing state
     * @param depth int representing depth
     */
    private static void  iterativedeepeningsearch(State curr_state, int depth) {
    	// States if goal has been found
    boolean foundstate = false;
     
    // count the depths 
    for(int depthsCountor = 0; depthsCountor < depth; depthsCountor++) {
    if (foundstate) { break; }
    //Starting place
        Stack<State> queu = new Stack();
        queu.push(curr_state);
         
        // Stores list
        State[] checkerstate = new State[0];
        // Stores the path 
        ArrayList<State> laststate = new ArrayList<State>();
        // Used to print 
        ArrayList<String> printstate = new ArrayList<String>();
        // Used to print path out
        ArrayList<String> stortestpath = new ArrayList<String>();
        
		 
        // Print starting state
        System.out.println(depthsCountor + ":" + curr_state.curr_jug1 + "" +
        curr_state.curr_jug2);
         
        while (!queu.isEmpty()) {
         
        // Pull out the first in queue
        State currstate = queu.pop();
        if(depthsCountor == 0) {
        String string3 = Integer.toString(currstate.curr_jug1) + 
        Integer.toString(currstate.curr_jug2);
        System.out.print(depthsCountor + ":" + string3 + " [] ");
        System.out.println("[" + string3 + "]");
        continue;
        }
        if(currstate.depth <= depthsCountor) {
        laststate.add(currstate);
        String string4 = Integer.toString(currstate.curr_jug1) + 
        Integer.toString(currstate.curr_jug2);
        stortestpath.add(string4);
        
        // Check goal state
        if (currstate.FindGoalState()) {
        System.out.println(string4 + " Goal");
        laststate.add(currstate);
        
        // Print out final path
        System.out.print("Path ");
        currstate.printPath(currstate);
        foundstate = true;
        break;
        }
        
        // Get possible successors
        checkerstate = currstate.getNextState();
        
        // Remove duplicates from  list
        for(int i = 0; i < checkerstate.length; i++) {
        String string5 = Integer.toString(checkerstate[i].curr_jug1) + 
        Integer.toString(checkerstate[i].curr_jug2);
        if(printstate.contains(string5) || stortestpath.contains(string5) || 
        checkerstate[i].depth > depthsCountor)  {
        continue;
        }
        queu.add(checkerstate[i]);
        printstate.add(string5);
        }
        }
         
        // Print  current queue
        String string6 = Integer.toString(currstate.curr_jug1) + 
        Integer.toString(currstate.curr_jug2);
        System.out.print(depthsCountor + ":" + string6 + " [");
        for (int i = 0; i < queu.size(); i++) {
        if (i + 1 == queu.size()) {
        System.out.print(printstate.get(i) + "]");
        break;
        }
        System.out.print(printstate.get(i) + ",");
        }
         
        // Pop
        if(!queu.isEmpty())
        printstate.remove(printstate.size()-1);
        else if (printstate.isEmpty()) {
        System.out.print("]");
        }
        // break while loop
        else {
        System.out.println( string6 + "]");
        break;
        }
        // Print out the path 
        System.out.print(" [");
        for (int i = 0; i < laststate.size(); i++) {
        if (i + 1 == laststate.size()) {
        System.out.println(stortestpath.get(i) + "]");
        break;
        }
        System.out.print(stortestpath.get(i) + ",");
        }
        }
    }
    }

    /**
     * 
     * run depth-first search algorithm
     * 
     * @param curr_state int representing state
     */
    private static void depthfirstsearch(State curr_state) {
        
    //Starting Queue
    Stack<State> queu = new Stack();
    queu.push(curr_state);
     
    // Stores list
    State[] checkerstate = new State[0];
    // Stores the path 
    ArrayList<State> laststate = new ArrayList<State>();
    // Print the queue out
    ArrayList<String> printstate = new ArrayList<String>();
    // Print the path out             
    ArrayList<String> stortestpath = new ArrayList<String>();
     
    // Print out starting state
    System.out.println(curr_state.curr_jug1 + "" + curr_state.curr_jug2);
     
    while (!queu.isEmpty()) {
     
    // Pull first in queue
    State newstate = queu.pop();
    laststate.add(newstate); 
    String newstring = Integer.toString(newstate.curr_jug1) + 
    Integer.toString(newstate.curr_jug2);
    stortestpath.add(newstring);
     
    // Check goal state
    if (newstate.FindGoalState()) {
    System.out.println(newstring + " Goal");
    laststate.add(newstate);
     
    // Print final path
    System.out.print("Path ");
    newstate.printPath(newstate);
    break;
    }
     
    // Get possible successors 
    checkerstate = newstate.getNextState();
     
    // Remove duplicates from the open queue list
    for(int i = 0; i < checkerstate.length; i++) {
    String string2 = Integer.toString(checkerstate[i].curr_jug1) + 
    Integer.toString(checkerstate[i].curr_jug2);
    if(printstate.contains(string2) || stortestpath.contains(string2)) {
    continue;
    }
    queu.add(checkerstate[i]);
    printstate.add(string2);
    }
     
    // Print current queue
    System.out.print(newstring + " [");
    for (int i = 0; i < queu.size(); i++) {
    if (i + 1 == queu.size()) {
    System.out.print(printstate.get(i) + "]");
    break;
    }
    System.out.print(printstate.get(i) + ",");
    }
     
    printstate.remove(printstate.size()-1);
     
    // Print out the path
    System.out.print(" [");
    for (int i = 0; i < laststate.size(); i++) {
    if (i + 1 == laststate.size()) {
    System.out.println(stortestpath.get(i) + "]");
    break;
    }
    System.out.print(stortestpath.get(i) + ",");
    }
    }
     
    }
    
    
	/**
	 * run breadth-first search algorithm 
	 * 
	 * @param curr_state int representing state
	 */
    private static void breadthfirstsearch(State curr_state) {
        
     
    //Starting Queue
    Queue<State> queu = new LinkedList();
    queu.add(curr_state);
     
    // Stores each list
    State[] checkerstate = new State[0];
    // Stores the path taken
    ArrayList<State> laststate = new ArrayList<State>();
    // Used to print out
    ArrayList<String> printstate = new ArrayList<String>();
    // Used to print path out
    ArrayList<String> stortestpath = new ArrayList<String>();
     
    // Print starting state
    System.out.println(curr_state.curr_jug1 + "" + 
    curr_state.curr_jug2);
     
    while (!queu.isEmpty()) {
    	
    // Pull first in queue
    State state1 = queu.remove();
    laststate.add(state1);
    String string1 = Integer.toString(state1.curr_jug1) + 
    Integer.toString(state1.curr_jug2);
    stortestpath.add(string1);
     
    // Check the goal state
    if (state1.FindGoalState()) {
    System.out.println(string1 + " Goal");
    laststate.add(state1);
     
    // Print final path
    System.out.print("Path ");
    state1.printPath(state1);
    break;
    }
     
    // Get successors
    checkerstate = state1.getNextState();
     
    // Remove duplicates from list
    for(int i = 0; i < checkerstate.length; i++) {
    String string2 = Integer.toString(checkerstate[i].curr_jug1) + 
    Integer.toString(checkerstate[i].curr_jug2);
    if(printstate.contains(string2) || stortestpath.contains(string2)) {
    continue;
    }
    queu.add(checkerstate[i]);
    printstate.add(string2);
    }
    
     
    // Print current queue
    System.out.print(string1 + " [");
    for (int i = 0; i < queu.size(); i++) {
    if (i + 1 == queu.size()) {
    System.out.print(printstate.get(i) + "]");
    break;
    }
    System.out.print(printstate.get(i) + ",");
    }
     
    printstate.remove(0);
     
    // Print path of states 
    System.out.print(" [");
    for (int i = 0; i < laststate.size(); i++) {
    if (i + 1 == laststate.size()) {
    System.out.println(stortestpath.get(i) + "]");
    break;
    }
    System.out.print(stortestpath.get(i) + ",");
    }
    }
    }
   
}


/**
 * 
 * Defines all the values and Lists
 * 
 * @author Mudit Joshi
 *
 */
class State {
    int cap_jug1;
    int cap_jug2;
    int curr_jug1;
    int curr_jug2;
    int goal;
    int depth;
    State parentPt;
    
    /**
     * 
     * Default Constructor
     * 
     * @param cap_jug1 int num
     * @param cap_jug2 int num
     * @param curr_jug1 int num
     * @param curr_jug2 int num
     * @param goal int num
     * @param depth int num
     * @param parent int num
     */
    public State(int cap_jug1, int cap_jug2, int curr_jug1, int curr_jug2, 
    int goal, int depth, State parent) {
        this.cap_jug1 = cap_jug1;
        this.cap_jug2 = cap_jug2;
        this.curr_jug1 = curr_jug1;
        this.curr_jug2 = curr_jug2;
        this.goal = goal;
        this.depth = depth;
        this.parentPt = parent;
    }
    
    /**
     * 
     * Find the Ordered Pair to print
     * 
     * @return next string
     */
    public String FindPairOrdered() {
        StringBuilder nextString = new StringBuilder();
        nextString.append(this.curr_jug1);
        nextString.append(this.curr_jug2);
        return nextString.toString().trim();
    }
    
    /**
     * 
     * Compare the two array lists
     * 
     * @param check
     * @param list
     * @return
     */
    private boolean comparenextState(State check, State[] list) {
     
    if (list.length == 0)
    return true;
    for(int i= 0; i < list.length; i++) {
    if (list[i] == null) {
    return true;
    }
    if (check.curr_jug1 == list[i].curr_jug1 && 
    check.curr_jug2 == list[i].curr_jug2) {
    return false;
    }
    }
     
    return true;
    }
    
    /**
     * 
     * Print the shortest path
     * 
     * @param last State
     */
    public void printPath(State last) {
    if (last.parentPt == null) {
    System.out.print(Integer.toString(last.curr_jug1) + "" +
    Integer.toString(last.curr_jug2));
    return;
    }
    String string1 = Integer.toString(last.curr_jug1) + "" + 
    Integer.toString(last.curr_jug2);
    printPath(last.parentPt);
    System.out.print(" " + string1);
    }
    
    /**
     * Return the next State
     * 
     * @return
     */
    public State[] getNextState() {

    ArrayList<State> Storearray = new ArrayList<State>();
     
    // Check if it's possible to empty jug1
    if(this.curr_jug1 > 0) {
    State state1 = new State(this.cap_jug1, this.cap_jug2, 0, 
    this.curr_jug2, this.goal, this.depth+1, this);
    Storearray.add(state1);
    }
     
    // Check if jug1 can fill jug2
    if(this.curr_jug1 > 0 && this.curr_jug2 < this.cap_jug2) {
    // Check if jug1 can fill and still have water
    if(this.curr_jug1 > this.cap_jug2 || this.curr_jug1 + 
    this.curr_jug2 >= this.cap_jug2) {
    int waterjug2 = this.cap_jug2;
    int waterjug1 = this.curr_jug1 - (this.cap_jug2 - this.curr_jug2);
    State state2 = new State(this.cap_jug1, this.cap_jug2, waterjug1, waterjug2,
    this.goal, this.depth+1, this);
    Storearray.add(state2);
    }
    // Check if jug1 can fill but not have water
    else if(this.curr_jug1 + this.curr_jug2 < this.cap_jug2) {
    int waterjug2 = this.curr_jug1 + this.curr_jug2;
    State state2 = new State(this.cap_jug1, this.cap_jug2, 0, waterjug2,
    this.goal, this.depth+1, this);
    Storearray.add(state2);
    }
    }

    // Check if it's possible to empty jug2
    if(this.curr_jug2 > 0) {
    State state3 = new State(this.cap_jug1, this.cap_jug2, this.curr_jug1,
    0, this.goal, this.depth+1, this);
    Storearray.add(state3);
    }
    // Check if it's possible to fill jug2
    if(this.curr_jug2 >= 0 && this.curr_jug2 < this.cap_jug2) {
    State state4 = new State(this.cap_jug1, this.cap_jug2, this.curr_jug1,
    this.cap_jug2, this.goal, this.depth+1, this);
    Storearray.add(state4);
    }
     
    // Check if jug2 can fill jug1
    if(this.curr_jug2 > 0 && this.curr_jug1 < this.cap_jug1) {
    // Check if jug1 can fill and still have water
    if(this.curr_jug2 > this.cap_jug1 || this.curr_jug1 + 
    this.curr_jug2 >= this.cap_jug1) {
    int waterjug1 = this.cap_jug1;
    int waterjug2 = this.curr_jug2 - (this.cap_jug1 - this.curr_jug1);
    State state5 = new State(this.cap_jug1, this.cap_jug2, waterjug1, waterjug2,
    this.goal, this.depth+1, this);
    Storearray.add(state5);
    }
    // Check if jug1 can fill but not have water
    else if(this.curr_jug1 + this.curr_jug2 < this.cap_jug1) {
    int waterjug1 = this.curr_jug1 + this.curr_jug2;
    State state6 = new State(this.cap_jug1, this.cap_jug2, waterjug1, 0, 
    this.goal, this.depth+1, this);
    Storearray.add(state6);
    }
    }
    // Check if it's possible to fill jug1
    if(this.curr_jug1 >= 0 && this.curr_jug1 < this.cap_jug1) {
    State state7 = new State(this.cap_jug1, this.cap_jug2, this.cap_jug1,
    this.curr_jug2, this.goal, this.depth+1, this);
    Storearray.add(state7);
    }

    State[] nextArray = new State[Storearray.size()];
    
    for (int i = 0; i < Storearray.size(); i++) {
    if(comparenextState(Storearray.get(i), nextArray)) {
    nextArray[i] = Storearray.get(i);
    }
    }
        return nextArray;
    }
    


    /**
     * 
     * determine if the state is a goal node or not and return boolean
     * 
     * @return boolean true or false
     */
    public boolean FindGoalState() {
        
    return (this.curr_jug1 == this.goal || this.curr_jug2 == this.goal);
    }

    public void printState(int option, int depth) {
     
    //UninformedSearch SearchList = new UninformedSearch();
    UninformedSearch.recursivecall(this, option, depth);

    }
    

}


/**
 * 
 * Main method Class
 * Takes the arguments and calls other methods
 * 
 * @author Mudit Joshi
 *
 */
public class WaterJug {

    public static void main(String args[]) {
        if (args.length != 6) {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }
        int flag = Integer.valueOf(args[0]);
        int cap_jug1 = Integer.valueOf(args[1]);
        int cap_jug2 = Integer.valueOf(args[2]);
        int curr_jug1 = Integer.valueOf(args[3]);
        int curr_jug2 = Integer.valueOf(args[4]);
        int goal = Integer.valueOf(args[5]);

        int option = flag / 100;
        int depth = flag % 100;

        if (option < 1 || option > 5) {
            System.out.println("Invalid flag input");
            return;
        }
        if (cap_jug1 > 9 || cap_jug2 > 9 || curr_jug1 > 9 || curr_jug2 > 9) {
            System.out.println("Invalid input: 2-digit jug volumes");
            return;
        }
        if (cap_jug1 < 0 || cap_jug2 < 0 || curr_jug1 < 0 || curr_jug2 < 0) {
            System.out.println("Invalid input: negative jug volumes");
            return;
        }
        if (cap_jug1 < curr_jug1 || cap_jug2 < curr_jug2) {
            System.out.println("Invalid input: jug volume exceeds its capacity");
            return;
        }
        State init = new State(cap_jug1, cap_jug2, curr_jug1, curr_jug2, goal, 
        0, null);
        init.printState(option, depth);
    }
}



