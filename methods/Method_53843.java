/** 
 * Unsafe version of  {@link #FileSizeProc(AIFileTellProcI) FileSizeProc}. 
 */
public static void nFileSizeProc(long struct,AIFileTellProcI value){
  memPutAddress(struct + AIFile.FILESIZEPROC,value.address());
}
