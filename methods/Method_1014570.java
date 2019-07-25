@Override public Status execute(Target dst,int dstIndex,Target src,int srcIndex,Status status){
  int count=src.get(srcIndex) % 16;
  int destination=dst.get(dstIndex);
  if (count == 1) {
    status.setOverflowFlag(false);
  }
  if ((destination & 0x8000) == 0x8000) {
    destination|=0xFFFF0000;
  }
  destination=destination >> (count - 1);
  status.setCarryFlag((destination & 1) == 1);
  destination=destination >> 1;
  status.setZeroFlag(destination == 0);
  dst.set(dstIndex,destination);
  return status;
}
