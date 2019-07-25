@Override public Status execute(Target dst,int dstIndex,Target src,int srcIndex,Status status){
  int count=src.get(srcIndex) % 16;
  int destination=dst.get(dstIndex);
  int signBit=(destination & 0x8000);
  destination=(destination << count) | (destination >>> (16 - count));
  if (count == 1) {
    status.setOverflowFlag((destination & 0x8000) != signBit);
  }
  status.setCarryFlag((destination & 1) == 1);
  dst.set(dstIndex,destination);
  return status;
}
