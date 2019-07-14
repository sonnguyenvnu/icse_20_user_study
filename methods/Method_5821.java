@Override public void maybeThrowError(int minRetryCount) throws IOException {
  if (fatalError != null) {
    throw fatalError;
  }
 else   if (currentTask != null) {
    currentTask.maybeThrowError(minRetryCount == Integer.MIN_VALUE ? currentTask.defaultMinRetryCount : minRetryCount);
  }
}
