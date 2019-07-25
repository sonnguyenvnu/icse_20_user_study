@Override public Status execute(Target dst,int dstIndex,int src,Status status){
  int count=src % 16;
  int destination=dst.get(dstIndex);
  int signBit=(destination & 0x8000);
  destination=(destination >>> count) | (destination << (16 - count));
  if (count == 1) {
    status.setOverflowFlag((destination & 0x8000) != signBit);
  }
  dst.set(dstIndex,destination);
  status.setCarryFlag((destination & 0x8000) == 0x8000);
  return status;
}
