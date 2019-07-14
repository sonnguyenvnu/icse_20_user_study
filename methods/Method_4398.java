/** 
 * Instantiates a new instance which uses Widevine CDM. Call  {@link #release()} when the instanceis no longer required.
 * @param defaultLicenseUrl The default license URL. Used for key requests that do not specifytheir own license URL.
 * @param httpDataSourceFactory A factory from which to obtain {@link HttpDataSource} instances.
 * @return A new instance which uses Widevine CDM.
 * @throws UnsupportedDrmException If the Widevine DRM scheme is unsupported or cannot beinstantiated.
 */
public static OfflineLicenseHelper<FrameworkMediaCrypto> newWidevineInstance(String defaultLicenseUrl,Factory httpDataSourceFactory) throws UnsupportedDrmException {
  return newWidevineInstance(defaultLicenseUrl,false,httpDataSourceFactory,null);
}
