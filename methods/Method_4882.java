private void setSourceDrmSession(@Nullable DrmSession<FrameworkMediaCrypto> session){
  DrmSession<FrameworkMediaCrypto> previous=sourceDrmSession;
  sourceDrmSession=session;
  releaseDrmSessionIfUnused(previous);
}
