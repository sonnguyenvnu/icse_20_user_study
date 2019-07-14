private void setCodecDrmSession(@Nullable DrmSession<FrameworkMediaCrypto> session){
  DrmSession<FrameworkMediaCrypto> previous=codecDrmSession;
  codecDrmSession=session;
  releaseDrmSessionIfUnused(previous);
}
