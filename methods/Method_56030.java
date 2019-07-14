/** 
 * Unsafe version of  {@link #textureAlign(int) textureAlign}. 
 */
public static void ntextureAlign(long struct,int value){
  UNSAFE.putInt(null,struct + CUdevprop.TEXTUREALIGN,value);
}
