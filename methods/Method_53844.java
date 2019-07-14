/** 
 * Unsafe version of  {@link #SeekProc(AIFileSeekI) SeekProc}. 
 */
public static void nSeekProc(long struct,AIFileSeekI value){
  memPutAddress(struct + AIFile.SEEKPROC,value.address());
}
