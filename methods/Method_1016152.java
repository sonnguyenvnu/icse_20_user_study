/** 
 * Cleanup cached reference to this generator. This is required mostly in cases when generator was not aborted.
 */
private void cleanup(){
synchronized (generatorsLock) {
    final ThumbnailGenerator generator=generators.get(element);
    if (generator == this) {
synchronized (element.getLock()) {
        element.setThumbnailQueued(false);
        element.setDisabledThumbnailQueued(false);
      }
      generators.remove(element);
    }
  }
}
