public void build(List<String> keys,int[] values){
  byte[][] byteKey=new byte[keys.size()][];
  Iterator<String> iteratorKey=keys.iterator();
  int i=0;
  while (iteratorKey.hasNext()) {
    byteKey[i]=iteratorKey.next().getBytes(utf8);
    ++i;
  }
  build(byteKey,values);
}
