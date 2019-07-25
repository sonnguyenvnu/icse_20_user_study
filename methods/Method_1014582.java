/** 
 * SETGE, SETNL       Greater or Equal, Not Less           SF=OF
 */
private static Status setge(Target dst,int dstIndex,Status status){
  boolean condition=status.isSignFlag() == status.isOverflowFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
