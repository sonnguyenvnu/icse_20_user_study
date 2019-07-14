/** 
 * Instantiates a new instance.
 * @param uuid The UUID of the drm scheme.
 * @param callback Performs key and provisioning requests.
 * @param optionalKeyRequestParameters An optional map of parameters to pass as the last argumentto  {@link ExoMediaDrm#getKeyRequest(byte[],List,int,HashMap)}. May be null.
 * @throws UnsupportedDrmException If the specified DRM scheme is not supported.
 */
public static DefaultDrmSessionManager<FrameworkMediaCrypto> newFrameworkInstance(UUID uuid,MediaDrmCallback callback,@Nullable HashMap<String,String> optionalKeyRequestParameters) throws UnsupportedDrmException {
  return new DefaultDrmSessionManager<>(uuid,FrameworkMediaDrm.newInstance(uuid),callback,optionalKeyRequestParameters,false,INITIAL_DRM_REQUEST_RETRY_COUNT);
}
