/** 
 * Unsafe version of:  {@link #bgfx_init init} 
 */
public static boolean nbgfx_init(long _init){
  long __functionAddress=Functions.init;
  if (CHECKS) {
    BGFXInit.validate(_init);
  }
  return invokePZ(_init,__functionAddress);
}
