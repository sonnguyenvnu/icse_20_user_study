/** 
 * SETNO              No Overflow                          OF=0
 */
private static Status setno(Target dst,int dstIndex,Status status){
  boolean condition=!status.isOverflowFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
