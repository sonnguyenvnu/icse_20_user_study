@Override public long open(DataSpec dataSpec) throws IOException {
  bytesRemaining=upstream.open(dataSpec);
  if (bytesRemaining == 0) {
    return 0;
  }
  if (dataSpec.length == C.LENGTH_UNSET && bytesRemaining != C.LENGTH_UNSET) {
    dataSpec=dataSpec.subrange(0,bytesRemaining);
  }
  dataSinkNeedsClosing=true;
  dataSink.open(dataSpec);
  return bytesRemaining;
}
