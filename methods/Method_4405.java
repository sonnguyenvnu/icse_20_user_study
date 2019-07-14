private byte[] blockingKeyRequest(@Mode int licenseMode,@Nullable byte[] offlineLicenseKeySetId,DrmInitData drmInitData) throws DrmSessionException {
  DrmSession<T> drmSession=openBlockingKeyRequest(licenseMode,offlineLicenseKeySetId,drmInitData);
  DrmSessionException error=drmSession.getError();
  byte[] keySetId=drmSession.getOfflineLicenseKeySetId();
  drmSessionManager.releaseSession(drmSession);
  if (error != null) {
    throw error;
  }
  return Assertions.checkNotNull(keySetId);
}
