/** 
 * SETBE, SETNA       Below or Equal, Not Above            CF=1 OR ZF=1
 */
private static Status setbe(Target dst,int dstIndex,Status status){
  boolean condition=status.isCarryFlag() || status.isZeroFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
