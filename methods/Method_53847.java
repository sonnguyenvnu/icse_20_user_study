/** 
 * Unsafe version of  {@link #OpenProc(AIFileOpenProcI) OpenProc}. 
 */
public static void nOpenProc(long struct,AIFileOpenProcI value){
  memPutAddress(struct + AIFileIO.OPENPROC,value.address());
}
