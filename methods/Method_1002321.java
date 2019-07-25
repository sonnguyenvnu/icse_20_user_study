@Override protected void inc(long[] cells,long offset,long delta){
  long v;
  do {
    v=UNSAFE.getLongVolatile(cells,offset);
  }
 while (!UNSAFE.compareAndSwapLong(cells,offset,v,v + delta));
}
