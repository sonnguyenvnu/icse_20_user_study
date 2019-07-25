private void update(Map<Character,Integer> map,Map<Integer,Integer> intMap,int num,char id,List<Character> list){
  if (map.containsKey(id)) {
    int count=map.get(id);
    intMap.put(num,count);
    while (count-- > 0) {
      for (      char c : list) {
        map.put(c,map.get(c) - 1);
      }
    }
  }
}
