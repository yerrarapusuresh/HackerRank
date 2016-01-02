import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Solution
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int numOfTestCases = in.nextInt();
		Graph g = null;
		while(numOfTestCases-- != 0)
		{
			int v = in.nextInt();
			int e = in.nextInt();
			g = new Graph(v);

			for(int i = 0; i < e; i++)
				g.addEdge(in.nextInt()-1,in.nextInt()-1);

			int source = in.nextInt()-1;
			BreadthFirstSearch bfs = new BreadthFirstSearch(g, source);
			int[] distance = bfs.distance();
			for(int i = 0; i < distance.length; i++)
			{
				if(i == source)
					continue;
				if(distance[i] == 0)
					System.out.print("-1 ");
				else
					System.out.print((distance[i] * 6)+" ");
			}
			System.out.println();
		}
		

	}
}

class Graph
{
	private final int V;
	private Bag[] adj;
	private int E;
	public Graph(int vertices)
	{
		V = vertices;
		E = 0;
		adj = new Bag[vertices];

		for(int i = 0; i < vertices; i++)
			adj[i] = new Bag();
	}

	public void addEdge(int v, int w)
	{
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}

	public int V()
	{
		return V;
	}
	public int edges()
	{
		return E;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < V; i++)
		{
			sb.append(String.format("%d : ", i));
			for(int v : adj[i])
				sb.append(String.format(" %d", v));
			sb.append(String.format("\n"));
		}

		return sb.toString();
	}
}

class BreadthFirstSearch
{
	boolean[] visited;
	int[] distance;
	int[] edgesto;
	private int s;

	public BreadthFirstSearch(Graph g, int s)
	{
		visited = new boolean[g.V()];
		distance = new int[g.V()];
		edgesto = new int[g.V()];
		this.s = s;
		bfs(g, s);
	}

	private void bfs(Graph g, int source)
	{
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[source] = true;
		queue.add(source);
		distance[source] = 0;
		while(queue.peek() != null)
		{
			int v = queue.remove();
			for(int w : g.adj(v))
			{
				if(!visited[w])
				{
					visited[w] = true;
					edgesto[w] = v;
					queue.add(w);
					distance[w] = distance[v]+1;

				}
			}
			
		}


	}

	public int[] distance()
	{
		return distance;
	}
}












class Bag implements Iterable<Integer> {
    private Node first;    // beginning of bag
    private int N;               // number of elements in bag

    // helper linked list class
    private static class Node
     {
        private int item;
        private Node next;
    }

    /**
     * Initializes an empty bag.
     */
    public Bag() {
        first = null;
        N = 0;
    }

    /**
     * Returns true if this bag is empty.
     *
     * @return <tt>true</tt> if this bag is empty;
     *         <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return N;
    }

    /**
     * Adds the item to this bag.
     *
     * @param  item the item to add to this bag
     */
    public void add(int item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


    /**
     * Returns an iterator that iterates over the items in this bag in arbitrary order.
     *
     * @return an iterator that iterates over the items in this bag in arbitrary order
     */
    public Iterator<Integer> iterator()  {
        return new ListIterator(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Integer> {
        private Node current;

        public ListIterator(Node first)
         {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            int item = current.item;
            current = current.next; 
            return item;
        }
    }

  

}



