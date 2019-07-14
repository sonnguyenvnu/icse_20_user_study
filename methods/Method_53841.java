/** 
 * Unsafe version of  {@link #ReadProc(AIFileReadProcI) ReadProc}. 
 */
public static void nReadProc(long struct,AIFileReadProcI value){
  memPutAddress(struct + AIFile.READPROC,value.address());
}
