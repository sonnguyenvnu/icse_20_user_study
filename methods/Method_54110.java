/** 
 * Unsafe version of  {@link #mRotation}. 
 */
public static float nmRotation(long struct){
  return UNSAFE.getFloat(null,struct + AIUVTransform.MROTATION);
}
