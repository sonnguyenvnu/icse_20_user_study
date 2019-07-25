/** 
 * SETS               Sign (negative)                      SF=1
 */
private static Status sets(Target dst,int dstIndex,Status status){
  boolean condition=status.isSignFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
