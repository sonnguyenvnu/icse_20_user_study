private static Map<Integer,Integer> read_map(ByteArray byteArray){
  int size=byteArray.nextInt();
  Map<Integer,Integer> map=new HashMap<Integer,Integer>();
  for (int i=0; i < size; i++) {
    map.put(byteArray.nextInt(),byteArray.nextInt());
  }
  return map;
}
