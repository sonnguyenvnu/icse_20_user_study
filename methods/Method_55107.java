/** 
 * Unsafe version of  {@link #win_gravity}. 
 */
public static int nwin_gravity(long struct){
  return UNSAFE.getInt(null,struct + XSetWindowAttributes.WIN_GRAVITY);
}
