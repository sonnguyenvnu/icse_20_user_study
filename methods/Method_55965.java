/** 
 * Unsafe version of  {@link #dstPitch(long) dstPitch}. 
 */
public static void ndstPitch(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTPITCH,value);
}
