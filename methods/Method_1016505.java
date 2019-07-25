public boolean get(final int pos){
  final int slot=pos / 5;
  if (pos < 0)   throw new RuntimeException("position out of bounds: " + pos);
  if (slot > bb.length)   return false;
  boolean b=((bb[slot] - 32) & (1 << (pos % 5))) > 0;
  return b;
}
