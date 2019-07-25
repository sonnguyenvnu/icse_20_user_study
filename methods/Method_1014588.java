/** 
 * SETNS              No Sign (positive)                   SF=0
 */
private static Status setns(Target dst,int dstIndex,Status status){
  boolean condition=!status.isSignFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
