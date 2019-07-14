/** 
 * @deprecated In most cases you may want to use {@link #hasEffectiveBroadcast()}.
 */
public boolean hasBroadcast(){
  ensureBroadcastAndIdFromArguments();
  return mBroadcast != null;
}
