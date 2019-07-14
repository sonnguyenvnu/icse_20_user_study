/** 
 * Unsafe version of  {@link #win_gravity(int) win_gravity}. 
 */
public static void nwin_gravity(long struct,int value){
  UNSAFE.putInt(null,struct + XSetWindowAttributes.WIN_GRAVITY,value);
}
