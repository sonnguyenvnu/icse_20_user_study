/** 
 * Unsafe version of  {@link #bits_per_rgb}. 
 */
public static int nbits_per_rgb(long struct){
  return UNSAFE.getInt(null,struct + XVisualInfo.BITS_PER_RGB);
}
