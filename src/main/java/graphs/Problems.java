
package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Problems {
    
    // https://leetcode.com/problems/bus-routes
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer,Set<Integer>> map = new HashMap();
        
        int n = routes.length;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<routes[i].length;j++){
                Set<Integer> set = map.getOrDefault(routes[i][j], new HashSet());
                set.add(i);
                map.put(routes[i][j], set);
            }
        }
        
        // BFS
        Queue<Integer> q = new LinkedList();
        Set<Integer> visitedBuses = new HashSet();
        Set<Integer> visitedStops = new HashSet();
        
        q.add(source);
        int level = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            
            while(size-->0){
                int temp = q.poll();
                if(visitedStops.contains(temp)) continue;
                
                if(temp == target) return level;
                
                Set<Integer> buses = map.get(temp);
                
                for(int bus : buses){
                    if(visitedBuses.contains(bus)) continue;
                    
                    int[] tempRoutes = routes[bus];
                    
                    for(int tempRoute: tempRoutes){
                        q.add(tempRoute);
                    }
                    
                    visitedBuses.add(bus);
                }
                
                visitedStops.add(temp);
            }
            
            level++;
        }
        
        
        return -1;
    }
    
    
    // dfs along with cycle checking
    private boolean dfs(int u, List<Integer>[] adj, Stack<Integer> stack, int[] visited) {
        visited[u] = 1;
        for (int v : adj[u]) {
            if (visited[v] == 1) return true;
            if (visited[v] == 0 && dfs(v, adj, stack, visited)) return true;
        }
        visited[u] = 2;
        stack.push(u);
        return false;
    }
    
    
    // topological sort
    // https://leetcode.com/problems/course-schedule-ii
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[numCourses];
        List<Integer>[] adj = new ArrayList[numCourses];
        
        // build adjacency list
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList();
        }
        
        for(int[] prerequisite : prerequisites) {
            adj[prerequisite[1]].add(prerequisite[1]);
        }
        
        // depth first search
        
        for(int i=0;i<numCourses; i++){
            // has cycle
            if(visited[i] == 0 && dfs(i,adj,stack,visited)) return new int[0];
        }
        
        int[] res = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        
        return res;
    }
}
