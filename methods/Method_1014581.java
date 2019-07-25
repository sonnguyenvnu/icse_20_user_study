/** 
 * SETG, SETNLE       Greater, Not Less or Equal           SF=OF AND ZF=0
 */
private static Status setg(Target dst,int dstIndex,Status status){
  boolean condition=(status.isSignFlag() == status.isOverflowFlag()) && !status.isZeroFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
