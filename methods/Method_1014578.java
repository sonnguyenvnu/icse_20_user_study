/** 
 * SETB, SETC,SETNAE  Below, Carry, Not Above or Equal     CF=1
 */
private static Status setb(Target dst,int dstIndex,Status status){
  boolean condition=status.isCarryFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
