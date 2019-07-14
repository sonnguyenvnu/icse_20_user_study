@Deprecated public void registerPageChangeListener(BannerListener listener){
  if (!listeners.contains(listener)) {
    listeners.add(listener);
  }
}
