/** 
 * Unsafe version of  {@link #background_pixel(long) background_pixel}. 
 */
public static void nbackground_pixel(long struct,long value){
  memPutAddress(struct + XSetWindowAttributes.BACKGROUND_PIXEL,value);
}
