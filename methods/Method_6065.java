private void maybeRenotifyRenderedFirstFrame(){
  if (renderedFirstFrame) {
    eventDispatcher.renderedFirstFrame(surface);
  }
}
