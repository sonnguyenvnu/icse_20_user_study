@Override public Status execute(Target dst,int dstIndex,Target src,int srcIndex,Status status){
  int count=src.get(srcIndex) % 17;
  int destination=dst.get(dstIndex);
  int signBit=(destination & 0x8000);
  if (status.isCarryFlag()) {
    destination|=0x10000;
  }
  destination=(destination << count) | (destination >>> (17 - count));
  status.setCarryFlag((destination & 0x10000) == 0x10000);
  if (count == 1) {
    status.setOverflowFlag((destination & 0x8000) != signBit);
  }
  dst.set(dstIndex,destination & 0xFFFF);
  return status;
}
