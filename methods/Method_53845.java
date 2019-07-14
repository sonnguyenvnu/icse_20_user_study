/** 
 * Unsafe version of  {@link #FlushProc(AIFileFlushProcI) FlushProc}. 
 */
public static void nFlushProc(long struct,AIFileFlushProcI value){
  memPutAddress(struct + AIFile.FLUSHPROC,value.address());
}
