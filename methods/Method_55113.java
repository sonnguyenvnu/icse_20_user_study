/** 
 * Unsafe version of  {@link #border_pixmap(long) border_pixmap}. 
 */
public static void nborder_pixmap(long struct,long value){
  memPutAddress(struct + XSetWindowAttributes.BORDER_PIXMAP,value);
}
