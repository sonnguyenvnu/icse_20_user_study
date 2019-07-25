@Setup public void init(){
  long[] bitmap=new long[1024];
  int cardinality=0;
  int targetCardinality=(int)(density * 65536);
  ThreadLocalRandom random=ThreadLocalRandom.current();
  while (cardinality < targetCardinality) {
    int index=random.nextInt(65536);
    long before=bitmap[index >>> 6];
    bitmap[index >>> 6]|=(1L << index);
    cardinality+=Long.bitCount(before ^ bitmap[index >>> 6]);
  }
  container=new BitmapContainer(bitmap,cardinality);
  bufferContainer=new MappeableBitmapContainer(LongBuffer.wrap(bitmap),cardinality);
}
