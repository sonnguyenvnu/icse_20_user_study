void maybeThrowError() throws IOException {
  loader.maybeThrowError(loadErrorHandlingPolicy.getMinimumLoadableRetryCount(dataType));
}
