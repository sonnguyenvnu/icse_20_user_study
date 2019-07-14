/** 
 * @deprecated Use {@link #newFrameworkInstance(UUID,MediaDrmCallback,HashMap)} and {@link #addListener(Handler,DefaultDrmSessionEventListener)}.
 */
@Deprecated public static DefaultDrmSessionManager<FrameworkMediaCrypto> newFrameworkInstance(UUID uuid,MediaDrmCallback callback,@Nullable HashMap<String,String> optionalKeyRequestParameters,@Nullable Handler eventHandler,@Nullable DefaultDrmSessionEventListener eventListener) throws UnsupportedDrmException {
  DefaultDrmSessionManager<FrameworkMediaCrypto> drmSessionManager=newFrameworkInstance(uuid,callback,optionalKeyRequestParameters);
  if (eventHandler != null && eventListener != null) {
    drmSessionManager.addListener(eventHandler,eventListener);
  }
  return drmSessionManager;
}
