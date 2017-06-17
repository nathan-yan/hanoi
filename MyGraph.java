import java.util.*;

public class MyGraph {
	public static boolean won (int state[][]){
		for (int i = state.length; i > 0; i--){
			if (state[state.length - 1][state.length - i] != i){
				return false;
			}
		}
		return true;
	}
	
	// Checks if the set is a valid set. 
	// It checks for the first number in the set that isn't 0, then checks if the newValue is smaller than that. If it is, return true. 
	public static boolean isValid(int state[], int newValue){
		for (int i = state.length - 1; i >= 0; i++){
			if (i != 0){
				if (newValue < i){
					return true;
				}
			}
		}
		return false;
	}
	
	// Inserts top disc from set1 to set2, returns true if successful transfer, false otherwise
	public static boolean insert(int set1[], int set2[]){
		for (int i = set1.length - 1; i >= 0; i--){
			if (set1[i] != 0){
				for (int j = set2.length - 1; j >= 0; j--){
					if (set2[j] != 0){
						if (set1[i] > set2[j]){
							return false;
						}
						
						if (j == set2.length - 1){
							return false;
						}
						
						set2[j + 1] = set1[i];
						set1[i] = 0;
						return true;
					}
				}
				
				set2[0] = set1[i];
				set1[i] = 0;
				return true;
			}
		}
		
		return false;
	}
	
	public static int[][] copyByData(int[][] original){
		int[][] newArray = new int[original.length][original[0].length];
		for (int i = 0; i < original.length; i++){
			for (int j = 0; j < original[0].length; j++){
				newArray[i][j] = original[i][j];
			}
		}
		
		return newArray;
	}
	
	public static String toString(int[][] state){
		String a = "";
		for (int[] b: state){
			a += Arrays.toString(b);
		}
		
		return a;
	} 
	
	
	public static void main (String args[]){
		int discs = 4;
		int initialState[][] = {{4, 3, 2, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};

		int currentState[][] = {{4, 3, 2, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};

		HashSet <Vertex> visited = new HashSet <Vertex>();
		Queue <Vertex> processingQ = new LinkedList <Vertex>();
		
		Vertex root = new Vertex("root", currentState, null);
		processingQ.offer(root);
		
		int vertices = 0;
		
		while(!processingQ.isEmpty()){
			// Look at all of the sets, and check if they can have anything taken out of them
			Vertex current = processingQ.poll();
			currentState = (int[][]) current.value;
			
			for (int s = 0; s < currentState.length; s++){
				if (currentState[s][0] != 0){
					// If this set can have something taken out of it, then we'll try placing items in the other sets
					int stateCopy[][] = copyByData(currentState);
					for (int s_ = 0; s_ < currentState.length; s_++){
						if (s_ != s){
							//System.out.println(toString(stateCopy) + " BEFORE");

							if (insert(stateCopy[s], stateCopy[s_])){
							//	System.out.println(toString(stateCopy) + " AFTER");
								// We make a new Vertex
								Vertex candidate = new Vertex(Integer.toString(vertices), stateCopy, current);
								
								if (won(stateCopy)){
									int[][][] history = new int[15][][]; 
									
									Vertex parent = candidate;
									int c = 0;
									
									while (parent.value[0][0] != 4 || parent.value[0][1] != 3 || parent.value[0][2] != 2 || parent.value[0][3] != 1){
										history[c] = parent.value;
										parent = parent.parent;	
										c ++;
										
									}

									for (int i = 0; i < 15; i++){
										System.out.println(toString(history[i]) + " - Step " + Integer.toString(15 - i));
									}
									
									System.out.println(toString(initialState) + " - Step 0");
									
									return;
								}
								
								// If we haven't already visited this vertex, place it into the processing queue. 
								if (!visited.contains(candidate)){
									
									visited.add(candidate);
									processingQ.add(candidate);
								}
							
								// Successful transfer means we reset currentState
								stateCopy = copyByData(currentState);
							}
						}
					}
				}
			}
			
		}
		
		System.out.println("TERMINATED");
		
	}
}











