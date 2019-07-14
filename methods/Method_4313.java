@Override public final void replaceStream(Format[] formats,SampleStream stream,long offsetUs) throws ExoPlaybackException {
  Assertions.checkState(!streamIsFinal);
  this.stream=stream;
  readEndOfStream=false;
  streamFormats=formats;
  streamOffsetUs=offsetUs;
  onStreamChanged(formats,offsetUs);
}
