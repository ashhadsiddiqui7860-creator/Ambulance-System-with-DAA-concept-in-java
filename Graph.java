package ambsys;

import java.util.*;

public class Graph 
{
    private Map<String, List<Node>> adjList = new HashMap<>();

    public void addEdge(String from, String to, int distance) 
    {
        from = from.toLowerCase();
        to = to.toLowerCase();

        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.putIfAbsent(to, new ArrayList<>());

        adjList.get(from).add(new Node(to, distance));
        adjList.get(to).add(new Node(from, distance));
    }

    public int getDistance(String start, String end) 
    {
        start = start.toLowerCase();
        end = end.toLowerCase();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        Map<String, Integer> dist = new HashMap<>();

        for (String key : adjList.keySet()) 
        {
            dist.put(key, Integer.MAX_VALUE);
        }

        dist.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) 
        {
            Node current = pq.poll();

            for (Node neighbor : adjList.getOrDefault(current.id, new ArrayList<>())) 
            {
                int newDist = dist.get(current.id) + neighbor.distance;

                if (newDist < dist.get(neighbor.id)) 
                {
                    dist.put(neighbor.id, newDist);
                    pq.add(new Node(neighbor.id, newDist));
                }
            }
        }

        return dist.getOrDefault(end, Integer.MAX_VALUE);
    }

    static class Node 
    {
        String id;
        int distance;

        Node(String id, int distance) 
        {
            this.id = id;
            this.distance = distance;
        }
    }
}
