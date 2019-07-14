@Override public final int supportsFormat(Format format) throws ExoPlaybackException {
  try {
    return supportsFormat(mediaCodecSelector,drmSessionManager,format);
  }
 catch (  DecoderQueryException e) {
    throw ExoPlaybackException.createForRenderer(e,getIndex());
  }
}
