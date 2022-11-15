package PS3;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class test {

    public static void main(String args[]){
        List<String> s = new ArrayList<>();
        s.add("abc");
        s.add("abcd");
        s.add("bc");
        s.add("adc");
        //System.out.println(countComplement(s));

        String s1 = "abca";
        String s2 = "acb";

        //ArrayList<Long> list = new ArrayList<>();
        HashMap<Long,long[]> map = new HashMap<>();
        long[] al = new long[26];
        int len = s1.length();
        int c = 0;
        int res = 0;
        for(int i = 0; i < len; i++){
            al[s1.charAt(i) - 'a']++;
        }


        for(int i = 0; i < s2.length(); i++){
            al[s2.charAt(i) - 'a']++;
        }
        for(int i = 0;  i < 26; i++){
            if(al[i] % 2 == 1){
                c++;
            }
        }
        if(c <= 1){
            res++;
        }
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(-1);
        list.add(-2);
        list.add(-3);
        list.add(7);
        list.add(8);


        // System.out.println(getMaximumEvenSum(list));

        HashMap<Integer, Integer> map1 = new HashMap<>();
        map1.put(1, 1);
        map1.put(1, map1.get(1) - 1);

        Point2D point2d_1 = new Point2D.Double(1.0, 2.0);


        System.out.println(point2d_1.distance(2, 0));
    }

    public static long getMaximumEvenSum(List<Integer> val) {
        // Write your code here

        long ans = 0;
        int odd = Integer.MAX_VALUE;
        for(int i = 0; i < val.size();i++){
            ans += val.get(i);
            System.out.println(val.get(i));

            if(val.get(i) % 2 == 1){
                odd = Math.min(Math.abs(val.get(i)), odd);
            }
        }

        if(ans % 2 == 0){
            return ans;
        }else{
            return ans;
        }


    }


   public static long countComplement(List<String> s){
        long ans = 0;
        HashMap<Long,Long> map = new HashMap<>();
        for(int i = 0; i < s.size();++i){
            String curr = s.get(i);
            long bitmask = 0;
            for(int j = 0; j < curr.length();j++){




            }
        }

        return ans;
   }
}


