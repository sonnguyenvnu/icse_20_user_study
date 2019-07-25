/** 
 * SETAE,SETNB,SETNC  Above or Equal, Not Below, No Carry  CF=0
 */
private static Status setae(Target dst,int dstIndex,Status status){
  boolean condition=!status.isCarryFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
