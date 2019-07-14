@Setup public void setup(){
  cache=cacheType.create(size);
  for (int i=0; i < size; i++) {
    cache.put(Integer.MIN_VALUE + i,Boolean.TRUE);
  }
}
