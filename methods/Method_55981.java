/** 
 * Unsafe version of  {@link #res_pitch2D_format(int) res_pitch2D_format}. 
 */
public static void nres_pitch2D_format(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_RESOURCE_DESC.RES_PITCH2D_FORMAT,value);
}
