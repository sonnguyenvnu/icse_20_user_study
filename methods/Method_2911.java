private static Map<Integer,Integer> read_map(IOUtil.LineIterator lineIterator){
  int size=Integer.valueOf(lineIterator.next());
  Map<Integer,Integer> map=new HashMap<Integer,Integer>();
  for (int i=0; i < size; i++) {
    String[] args=lineIterator.next().split("\t");
    map.put(Integer.valueOf(args[0]),Integer.valueOf(args[1]));
  }
  return map;
}
