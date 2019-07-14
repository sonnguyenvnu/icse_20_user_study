/** 
 * Unsafe version of  {@link #visual}. 
 */
public static Visual nvisual(long struct){
  return Visual.create(memGetAddress(struct + XVisualInfo.VISUAL));
}
