/** 
 * SETO               Overflow                             OF=1
 */
private static Status seto(Target dst,int dstIndex,Status status){
  boolean condition=status.isOverflowFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
