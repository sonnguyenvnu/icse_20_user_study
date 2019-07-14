/** 
 * Unsafe version of  {@link #save_under}. 
 */
public static int nsave_under(long struct){
  return UNSAFE.getInt(null,struct + XSetWindowAttributes.SAVE_UNDER);
}
