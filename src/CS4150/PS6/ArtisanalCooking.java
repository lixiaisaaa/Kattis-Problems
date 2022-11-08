package PS6;
import java.util.*;
import java.util.Scanner;

public class ArtisanalCooking {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        //col and row
        int foods =  s.nextInt();
        int dependencies = s.nextInt();
        HashMap<Integer, HashMap<Integer,Integer>> graph = new HashMap();
        HashMap<Integer, Integer> indegree = new HashMap();
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> preorder = new LinkedList<>();
        int[] res = new int[foods];

        //2nd line
        for(int i = 0 ; i < foods; i++){
            int num =  s.nextInt();
            res[i] = num;
        }

        for(int i = 0; i <= dependencies;i++){
            indegree.put(i,0);
        }
      //System.out.println("indegree: " + indegree);

        //Construct the graph with M lines
        int u,v,w;
        for(int i = 0; i < dependencies; i++){
            u = s.nextInt();
            v = s.nextInt();
            w = s.nextInt();
            HashMap<Integer,Integer> pair = graph.getOrDefault(v, new HashMap<>());
            pair.put(u, w);
            graph.put(v,pair);
            indegree.put(u,indegree.getOrDefault(u,0) + 1);
        }
        System.out.println("g : " + graph);
        System.out.println("indegree: " + indegree);

        for(int i = 0; i < foods;i++){
            if(indegree.get(i) == 0){
                preorder.offer(i);
            }
        }
        System.out.println("preorder: "+ preorder);

        //Produce a Topological order queue
        //preorder: 4
        while(!preorder.isEmpty()){
            int curr = preorder.poll();
            queue.offer(curr);
            if(!graph.containsKey(curr)) continue;

            for(Map.Entry<Integer,Integer> map : graph.get(curr).entrySet()){
                int key = map.getKey();
                indegree.put(key,indegree.get(key)-1);
                if(indegree.get(key) == 0){
                    preorder.offer(key);
                }
            }
        }
        System.out.println("Queue is : " + queue);


        //Add Weighted
        while(!queue.isEmpty()){
            int supplies = queue.poll();

            if(!graph.containsKey(supplies)) continue;

            for(Map.Entry<Integer,Integer> map : graph.get(supplies).entrySet()){
                int key = map.getKey();
                int value = map.getValue();
                res[key] = res[key] + value * res[supplies];
            }
        }

        //Print result
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<res.length;i++) {
            list.add(res[i]);
        }
        System.out.println(list);
    }
}
