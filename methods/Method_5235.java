@Override public void maybeThrowError() throws IOException {
  if (fatalError != null) {
    throw fatalError;
  }
 else {
    manifestLoaderErrorThrower.maybeThrowError();
  }
}
