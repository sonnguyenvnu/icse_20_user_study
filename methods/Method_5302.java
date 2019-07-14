@Override public void maybeThrowPrepareError() throws IOException {
  try {
    if (mediaPeriod != null) {
      mediaPeriod.maybeThrowPrepareError();
    }
 else {
      mediaSource.maybeThrowSourceInfoRefreshError();
    }
  }
 catch (  final IOException e) {
    if (listener == null) {
      throw e;
    }
    if (!notifiedPrepareError) {
      notifiedPrepareError=true;
      listener.onPrepareError(id,e);
    }
  }
}
