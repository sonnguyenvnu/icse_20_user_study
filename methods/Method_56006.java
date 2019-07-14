/** 
 * Unsafe version of  {@link #borderColor(int,float) borderColor}. 
 */
public static void nborderColor(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + CUDA_TEXTURE_DESC.BORDERCOLOR + check(index,4) * 4,value);
}
