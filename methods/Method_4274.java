private void setSourceDrmSession(@Nullable DrmSession<ExoMediaCrypto> session){
  DrmSession<ExoMediaCrypto> previous=sourceDrmSession;
  sourceDrmSession=session;
  releaseDrmSessionIfUnused(previous);
}
