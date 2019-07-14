/** 
 * Unsafe version of  {@link #SeekProc}. 
 */
public static AIFileSeek nSeekProc(long struct){
  return AIFileSeek.create(memGetAddress(struct + AIFile.SEEKPROC));
}
