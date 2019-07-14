/** 
 * Unsafe version of  {@link #res_pitch2D_format}. 
 */
public static int nres_pitch2D_format(long struct){
  return UNSAFE.getInt(null,struct + CUDA_RESOURCE_DESC.RES_PITCH2D_FORMAT);
}
