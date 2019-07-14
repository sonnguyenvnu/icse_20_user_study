/** 
 * @deprecated Use {@link #newPlayReadyInstance(MediaDrmCallback,String)} and {@link #addListener(Handler,DefaultDrmSessionEventListener)}.
 */
@Deprecated public static DefaultDrmSessionManager<FrameworkMediaCrypto> newPlayReadyInstance(MediaDrmCallback callback,@Nullable String customData,@Nullable Handler eventHandler,@Nullable DefaultDrmSessionEventListener eventListener) throws UnsupportedDrmException {
  DefaultDrmSessionManager<FrameworkMediaCrypto> drmSessionManager=newPlayReadyInstance(callback,customData);
  if (eventHandler != null && eventListener != null) {
    drmSessionManager.addListener(eventHandler,eventListener);
  }
  return drmSessionManager;
}
