import java.util.*;

public class Vertex implements Comparable{
	public String label;
	public List<Edge> edges;
	
	public int[][] value;
	
	public int cost;
	public Vertex parent;
	
	public Vertex(String label, int[][] value, Vertex parent){
		edges = new ArrayList();
		this.label = label;
		
		this.value = value;
		this.parent = parent;
		
	}
	
	public void putEdge(Vertex other){
		edges.add(new Edge(this, other));
		other.edges.add(new Edge(this, other));
		
	}
	
	public String toString(){
		return label;
	}
	
	@Override
	public int hashCode(){
		return Arrays.hashCode(this.value[0]) * 7 + Arrays.hashCode(this.value[1]) * 49 + Arrays.hashCode(this.value[2]) * 49 * 7;
	}
	
	@Override 
	public boolean equals(Object other){
		if (other instanceof Vertex){
			return Arrays.deepEquals((((Vertex) other).value), value);
		}else{
			return false;
		}
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
