
package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


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
}
