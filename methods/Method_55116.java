/** 
 * Unsafe version of  {@link #backing_pixel(long) backing_pixel}. 
 */
public static void nbacking_pixel(long struct,long value){
  memPutAddress(struct + XSetWindowAttributes.BACKING_PIXEL,value);
}
