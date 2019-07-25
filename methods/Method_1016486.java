public boolean get(final int pos){
  assert (pos >= 0);
  final int slot=pos >> 3;
  if (slot >= this.bb.length)   return false;
  return (this.bb[slot] & (1 << (pos % 8))) > 0;
}
