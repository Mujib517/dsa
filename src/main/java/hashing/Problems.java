/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package hashing;

import java.util.HashMap;
import java.util.Map;


public class Problems {
    public boolean wordPattern(String pattern, String s) {
        String[] tokens = s.split("\\s+");
        
        if (tokens.length != pattern.length()) {
            return false;
        }
        
        // for edge case maintain two stacks
        // ex: abba -> "dog cat cat fish"
        Map<String, Character> map = new HashMap();
        Map<Character, String> map2 = new HashMap();
        
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (map.containsKey(token)) {
                continue;
            }
            if (map2.containsKey(pattern.charAt(i))) {
                continue;
            }
            
            map.put(token, pattern.charAt(i));
            map2.put(pattern.charAt(i), token);
        }
        
        String res = "";
        for (int i = 0; i < tokens.length; i++) {
            res += map.get(tokens[i]);
        }
        
        return res.equals(pattern);
    }
}
