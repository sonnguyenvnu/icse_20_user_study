public String minWindow(String s,String t){
  HashMap<Character,Integer> map=new HashMap<>();
  for (  char c : s.toCharArray()) {
    map.put(c,0);
  }
  for (  char c : t.toCharArray()) {
    if (map.containsKey(c)) {
      map.put(c,map.get(c) + 1);
    }
 else {
      return "";
    }
  }
  int start=0;
  int end=0;
  int minStart=0;
  int minLength=Integer.MAX_VALUE;
  int counter=t.length();
  while (end < s.length()) {
    char c1=s.charAt(end);
    if (map.get(c1) > 0) {
      counter--;
    }
    map.put(c1,map.get(c1) - 1);
    end++;
    while (counter == 0) {
      if (minLength > end - start) {
        minLength=end - start;
        minStart=start;
      }
      char c2=s.charAt(start);
      map.put(c2,map.get(c2) + 1);
      if (map.get(c2) > 0) {
        counter++;
      }
      start++;
    }
  }
  return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart,minStart + minLength);
}
