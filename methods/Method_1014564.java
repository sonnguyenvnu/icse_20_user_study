@Override public Status execute(Target dst,int dstIndex,Target src,int srcIndex,Status status){
  int count=src.get(srcIndex) % 17;
  int destination=dst.get(dstIndex) << 1;
  int signBit=(destination & 0x10000);
  if (status.isCarryFlag()) {
    destination|=1;
  }
  destination=(destination >>> count) | (destination << (17 - count));
  status.setCarryFlag((destination & 1) == 1);
  if (count == 1) {
    status.setOverflowFlag((destination & 0x10000) != signBit);
  }
  dst.set(dstIndex,destination >> 1);
  return status;
}
