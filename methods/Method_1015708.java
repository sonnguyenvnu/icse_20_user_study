@GuardedBy("lock") protected void _grow(long new_capacity){
  int new_cap=Util.getNextHigherPowerOfTwo((int)Math.max(buffer.length,new_capacity));
  if (new_cap == buffer.length)   return;
  _copy(new_cap);
}
