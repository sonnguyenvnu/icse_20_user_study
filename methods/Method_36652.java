public void register(IScrollListener scrollListener){
  if (!scrollListeners.contains(scrollListener)) {
    scrollListeners.add(scrollListener);
  }
}
