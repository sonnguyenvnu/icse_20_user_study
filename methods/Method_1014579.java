/** 
 * SETE, SETZ         Equal, Zero                          ZF=1
 */
private static Status sete(Target dst,int dstIndex,Status status){
  boolean condition=status.isZeroFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
