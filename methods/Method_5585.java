@Override protected void onStreamChanged(Format[] formats,long offsetUs) throws ExoPlaybackException {
  streamFormat=formats[0];
  if (decoder != null) {
    decoderReplacementState=REPLACEMENT_STATE_SIGNAL_END_OF_STREAM;
  }
 else {
    decoder=decoderFactory.createDecoder(streamFormat);
  }
}
