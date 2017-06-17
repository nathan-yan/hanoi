import java.util.*;

public class Edge implements Comparable{
	public Map<Vertex, Vertex> edgeMap;
	
	public Edge(Vertex a, Vertex b){
		edgeMap = new HashMap();
		edgeMap.put(a, b);
		edgeMap.put(b, a);
		
	}
	
	public Vertex getNext(Vertex v){
		return edgeMap.get(v);
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
	
}
