@Override public void maybeThrowPrepareError() throws IOException {
  for (  HlsSampleStreamWrapper sampleStreamWrapper : sampleStreamWrappers) {
    sampleStreamWrapper.maybeThrowPrepareError();
  }
}
