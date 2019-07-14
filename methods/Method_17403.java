@Override public boolean mightContain(long e){
  int item=spread(Long.hashCode(e));
  for (int i=0; i < 4; i++) {
    int hash=seeded(item,i);
    int index=hash >>> tableShift;
    if ((table[index] & bitmask(hash)) == 0L) {
      return false;
    }
  }
  return true;
}
