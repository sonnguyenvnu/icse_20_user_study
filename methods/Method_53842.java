/** 
 * Unsafe version of  {@link #TellProc(AIFileTellProcI) TellProc}. 
 */
public static void nTellProc(long struct,AIFileTellProcI value){
  memPutAddress(struct + AIFile.TELLPROC,value.address());
}
