/** 
 * Unsafe version of  {@link #screen_shot}. 
 */
public static BGFXScreenShotCallback nscreen_shot(long struct){
  return BGFXScreenShotCallback.create(memGetAddress(struct + BGFXCallbackVtbl.SCREEN_SHOT));
}
