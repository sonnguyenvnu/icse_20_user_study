/** 
 * SETNE, SETNZ       Not Equal, Not Zero                  ZF=0
 */
private static Status setne(Target dst,int dstIndex,Status status){
  boolean condition=!status.isZeroFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
