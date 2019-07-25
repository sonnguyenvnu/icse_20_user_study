@Override public long step(long pos){
  return (pos + SCALE) & capacityMask2;
}
