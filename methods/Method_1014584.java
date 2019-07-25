/** 
 * SETL, SETNGE       Less, Not Greater or Equal           SF<>OF
 */
private static Status setl(Target dst,int dstIndex,Status status){
  boolean condition=status.isSignFlag() != status.isOverflowFlag();
  int value=condition ? 1 : 0;
  dst.set(dstIndex,value);
  return status;
}
