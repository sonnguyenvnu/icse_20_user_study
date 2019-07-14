public int leastBricks(List<List<Integer>> wall){
  Map<Integer,Integer> map=new HashMap<>();
  int width=0, max=0;
  for (  List<Integer> sub : wall) {
    int p=0;
    for (int i=0, len=sub.size() - 1; i < len; ++i) {
      p+=sub.get(i);
      Integer v=map.get(p);
      map.put(p,(v == null ? 0 : v) + 1);
    }
  }
  for (  Integer integer : map.values()) {
    if (integer > max)     max=integer;
  }
  return wall.size() - max;
}
