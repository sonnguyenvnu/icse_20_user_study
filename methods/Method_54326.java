/** 
 * Unsafe version of  {@link #callback}. 
 */
@Nullable public static BGFXCallbackInterface ncallback(long struct){
  return BGFXCallbackInterface.createSafe(memGetAddress(struct + BGFXInit.CALLBACK));
}
