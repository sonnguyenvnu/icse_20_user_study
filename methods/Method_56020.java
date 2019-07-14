/** 
 * Unsafe version of  {@link #textureAlign}. 
 */
public static int ntextureAlign(long struct){
  return UNSAFE.getInt(null,struct + CUdevprop.TEXTUREALIGN);
}
