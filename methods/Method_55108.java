/** 
 * Unsafe version of  {@link #backing_store}. 
 */
public static int nbacking_store(long struct){
  return UNSAFE.getInt(null,struct + XSetWindowAttributes.BACKING_STORE);
}
