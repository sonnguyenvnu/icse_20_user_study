/** 
 * Unsafe version of  {@link #bits_per_rgb(int) bits_per_rgb}. 
 */
public static void nbits_per_rgb(long struct,int value){
  UNSAFE.putInt(null,struct + XVisualInfo.BITS_PER_RGB,value);
}
