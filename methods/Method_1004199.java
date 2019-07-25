@Override public final void close(){
  if (resources.releaseManually()) {
    cleanupOnClose();
  }
}
