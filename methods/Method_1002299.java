private void initialize(final int initial_sz){
  RangeUtil.checkPositiveOrZero(initial_sz,"initial_sz");
  int i;
  for (i=MIN_SIZE_LOG; (1 << i) < initial_sz; i++) {
  }
  _chm=new CHM(this,new ConcurrentAutoTable(),i);
  _val_1=TOMBSTONE;
  _last_resize_milli=System.currentTimeMillis();
}
