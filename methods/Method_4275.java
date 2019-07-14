private void setDecoderDrmSession(@Nullable DrmSession<ExoMediaCrypto> session){
  DrmSession<ExoMediaCrypto> previous=decoderDrmSession;
  decoderDrmSession=session;
  releaseDrmSessionIfUnused(previous);
}
