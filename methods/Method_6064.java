void maybeNotifyRenderedFirstFrame(){
  if (!renderedFirstFrame) {
    renderedFirstFrame=true;
    eventDispatcher.renderedFirstFrame(surface);
  }
}
