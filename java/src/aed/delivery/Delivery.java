package aed.delivery;

import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;

public class Delivery<V> {	

	private DirectedGraph<V, Integer> mapa;
	private Map<Vertex<V>,Integer> vertex2integer;
	private Integer[][] array;

	// Construct a graph out of a series of vertices and an adjacency matrix.
	// There are 'len' vertices. A null means no connection. A non-negative
	// number represents distance between nodes.
	public Delivery(V[] places, Integer[][] gmat) {
		array = gmat;
		mapa = new DirectedAdjacencyListGraph<>();
		vertex2integer = new HashTableMap<>();
		IndexedList<Vertex<V>> traducciones = new ArrayIndexedList<>();
		for(int i=0;i<places.length;i++) {
			Vertex<V> nuevo =  mapa.insertVertex(places[i]);
			traducciones.add(i, nuevo);
			vertex2integer.put(nuevo, i);
		}

		for(int i=0;i<gmat.length;i++) {
			Vertex<V> origen = traducciones.get(i);
			for(int j=0;j<gmat[0].length;j++) {
				if(gmat[i][j]!=null) {
					mapa.insertDirectedEdge(origen,traducciones.get(j),gmat[i][j]);
				}
			}
		}

	}

	// Just return the graph that was constructed
	public DirectedGraph<V, Integer> getGraph() {
		return mapa;
	}

	// Return a Hamiltonian path for the stored graph, or null if there is none.
	// The list containts a series of vertices, with no repetitions (even if the path
	// can be expanded to a cycle).
	public PositionList <Vertex<V>> tour() {
		PositionList<Vertex<V>> path = new NodePositionList<>();
		Set<Vertex<V>> visited = new HashTableMapSet<>();

		return tourInicio( path, visited);

	}

	private PositionList <Vertex<V>> tourInicio( PositionList<Vertex<V>> path, Set<Vertex<V>> visited) {


		for(Vertex<V> v : mapa.vertices()) {

			path.addLast(v);
			visited.add(v);
			PositionList<Vertex<V>> res=tour(v,path,visited);

			if(res !=null) {
				return res;
			}
			path.remove(path.last());
			visited.remove(v);

		}
		return null;

	}


	private PositionList <Vertex<V>> tour(Vertex<V> cursor, PositionList<Vertex<V>> path, Set<Vertex<V>> visited) {

		if(mapa.size()==visited.size()) {
			return path;
		}

		for(Edge<Integer> e : mapa.outgoingEdges(cursor)) {
			Vertex<V> siguiente = mapa.endVertex(e);
			if(!visited.contains(siguiente)) {
				path.addLast(siguiente);
				visited.add(siguiente);
				PositionList<Vertex<V>> res=tour(siguiente,path,visited);
				if(res !=null) {
					return res;
				}
				path.remove(path.last());
				visited.remove(siguiente);

			}
		}
		return null;
	}

	public int length(PositionList<Vertex<V>> path) {
		int longitud=0;
		Position<Vertex<V>> origen = path.first();
		Position<Vertex<V>> destino = path.next(origen);

		while(destino!=null) {
			longitud+=array[vertex2integer.get(origen.element())][vertex2integer.get(destino.element())];  
			origen = destino;
			destino = path.next(destino);

		}

		return longitud;

	}

	public String toString() {
		return "Delivery";
	}
}
