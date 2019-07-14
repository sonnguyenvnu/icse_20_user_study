/** 
 * Unsafe version of  {@link #override_redirect(boolean) override_redirect}. 
 */
public static void noverride_redirect(long struct,int value){
  UNSAFE.putInt(null,struct + XSetWindowAttributes.OVERRIDE_REDIRECT,value);
}
