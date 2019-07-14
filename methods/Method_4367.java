/** 
 * @deprecated Use {@link #newWidevineInstance(MediaDrmCallback,HashMap)} and {@link #addListener(Handler,DefaultDrmSessionEventListener)}.
 */
@Deprecated public static DefaultDrmSessionManager<FrameworkMediaCrypto> newWidevineInstance(MediaDrmCallback callback,@Nullable HashMap<String,String> optionalKeyRequestParameters,@Nullable Handler eventHandler,@Nullable DefaultDrmSessionEventListener eventListener) throws UnsupportedDrmException {
  DefaultDrmSessionManager<FrameworkMediaCrypto> drmSessionManager=newWidevineInstance(callback,optionalKeyRequestParameters);
  if (eventHandler != null && eventListener != null) {
    drmSessionManager.addListener(eventHandler,eventListener);
  }
  return drmSessionManager;
}
