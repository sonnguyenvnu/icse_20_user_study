/** 
 * Creates an instance for the specified scheme UUID.
 * @param uuid The scheme uuid.
 * @return The created instance.
 * @throws UnsupportedDrmException If the DRM scheme is unsupported or cannot be instantiated.
 */
public static FrameworkMediaDrm newInstance(UUID uuid) throws UnsupportedDrmException {
  try {
    return new FrameworkMediaDrm(uuid);
  }
 catch (  UnsupportedSchemeException e) {
    throw new UnsupportedDrmException(UnsupportedDrmException.REASON_UNSUPPORTED_SCHEME,e);
  }
catch (  Exception e) {
    throw new UnsupportedDrmException(UnsupportedDrmException.REASON_INSTANTIATION_ERROR,e);
  }
}
