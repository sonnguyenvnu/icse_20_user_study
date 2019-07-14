/** 
 * Downloads an offline license.
 * @param drmInitData The {@link DrmInitData} for the content whose license is to be downloaded.
 * @return The key set id for the downloaded license.
 * @throws DrmSessionException Thrown when a DRM session error occurs.
 */
public synchronized byte[] downloadLicense(DrmInitData drmInitData) throws DrmSessionException {
  Assertions.checkArgument(drmInitData != null);
  return blockingKeyRequest(DefaultDrmSessionManager.MODE_DOWNLOAD,null,drmInitData);
}
