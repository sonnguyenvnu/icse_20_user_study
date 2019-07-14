/** 
 * Unsafe version of  {@link #background_pixmap(long) background_pixmap}. 
 */
public static void nbackground_pixmap(long struct,long value){
  memPutAddress(struct + XSetWindowAttributes.BACKGROUND_PIXMAP,value);
}
