@Setup public void setup(){
  ints=new Integer[SIZE];
  cache=cacheType.create(2 * SIZE);
  for (int i=0; i < 2 * SIZE; i++) {
    cache.put(i,Boolean.TRUE);
  }
  cache.clear();
  cache.cleanUp();
  NumberGenerator generator=new ScrambledZipfianGenerator(ITEMS);
  for (int i=0; i < SIZE; i++) {
    ints[i]=generator.nextValue().intValue();
    cache.put(ints[i],Boolean.TRUE);
  }
}
