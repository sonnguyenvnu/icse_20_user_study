/** 
 * Releases an offline license.
 * @param offlineLicenseKeySetId The key set id of the license to be released.
 * @throws DrmSessionException Thrown when a DRM session error occurs.
 */
public synchronized void releaseLicense(byte[] offlineLicenseKeySetId) throws DrmSessionException {
  Assertions.checkNotNull(offlineLicenseKeySetId);
  blockingKeyRequest(DefaultDrmSessionManager.MODE_RELEASE,offlineLicenseKeySetId,DUMMY_DRM_INIT_DATA);
}
