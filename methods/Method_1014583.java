/** 
 * SETLE, SETNG       Less or Equal, Not Greater           SF<>OF OR ZF=1
 */
private static Status setle(Target dst,int dstIndex,Status status){
  boolean condition=(status.isSignFlag() != status.isOverflowFlag()) || status.isZeroFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
