/** 
 * Unsafe version of  {@link #res_reserved_reserved(IntBuffer) res_reserved_reserved}. 
 */
public static void nres_reserved_reserved(long struct,IntBuffer value){
  if (CHECKS) {
    checkGT(value,32);
  }
  memCopy(memAddress(value),struct + CUDA_RESOURCE_DESC.RES_RESERVED_RESERVED,value.remaining() * 4);
}
