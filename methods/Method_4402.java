/** 
 * Renews an offline license.
 * @param offlineLicenseKeySetId The key set id of the license to be renewed.
 * @return The renewed offline license key set id.
 * @throws DrmSessionException Thrown when a DRM session error occurs.
 */
public synchronized byte[] renewLicense(byte[] offlineLicenseKeySetId) throws DrmSessionException {
  Assertions.checkNotNull(offlineLicenseKeySetId);
  return blockingKeyRequest(DefaultDrmSessionManager.MODE_DOWNLOAD,offlineLicenseKeySetId,DUMMY_DRM_INIT_DATA);
}
