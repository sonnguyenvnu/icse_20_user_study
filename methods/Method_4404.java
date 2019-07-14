/** 
 * Returns the remaining license and playback durations in seconds, for an offline license.
 * @param offlineLicenseKeySetId The key set id of the license.
 * @return The remaining license and playback durations, in seconds.
 * @throws DrmSessionException Thrown when a DRM session error occurs.
 */
public synchronized Pair<Long,Long> getLicenseDurationRemainingSec(byte[] offlineLicenseKeySetId) throws DrmSessionException {
  Assertions.checkNotNull(offlineLicenseKeySetId);
  DrmSession<T> drmSession=openBlockingKeyRequest(DefaultDrmSessionManager.MODE_QUERY,offlineLicenseKeySetId,DUMMY_DRM_INIT_DATA);
  DrmSessionException error=drmSession.getError();
  Pair<Long,Long> licenseDurationRemainingSec=WidevineUtil.getLicenseDurationRemainingSec(drmSession);
  drmSessionManager.releaseSession(drmSession);
  if (error != null) {
    if (error.getCause() instanceof KeysExpiredException) {
      return Pair.create(0L,0L);
    }
    throw error;
  }
  return Assertions.checkNotNull(licenseDurationRemainingSec);
}
