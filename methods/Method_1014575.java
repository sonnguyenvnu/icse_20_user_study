/** 
 * SETA, SETNBE   Above, Not Below or Equal   CF=0 AND ZF=0
 */
private static Status seta(Target dst,int dstIndex,Status status){
  boolean condition=!status.isCarryFlag() && !status.isZeroFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
