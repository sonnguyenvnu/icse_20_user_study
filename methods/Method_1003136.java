private long allocate(int length,boolean allocate){
  int blocks=getBlockCount(length);
  for (int i=0; ; ) {
    int start=set.nextClearBit(i);
    int end=set.nextSetBit(start + 1);
    if (end < 0 || end - start >= blocks) {
      assert set.nextSetBit(start) == -1 || set.nextSetBit(start) >= start + blocks : "Double alloc: " + Integer.toHexString(start) + "/" + Integer.toHexString(blocks) + " " + this;
      if (allocate) {
        set.set(start,start + blocks);
      }
 else {
        failureFlags<<=1;
        if (end < 0) {
          failureFlags|=1;
        }
      }
      return getPos(start);
    }
    i=end;
  }
}
