/** 
 * Returns the value of the  {@code SeekProc} field. 
 */
@NativeType("aiFileSeek") public AIFileSeek SeekProc(){
  return nSeekProc(address());
}
