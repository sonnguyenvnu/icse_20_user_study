@Override public void onPageScrollStateChanged(int state){
  if (bannerSupport != null) {
    for (int j=0; j < bannerSupport.getListeners().size(); j++) {
      BannerListener listener=bannerSupport.getListeners().get(j);
      listener.onPageScrollStateChanged(state);
    }
  }
  if (bannerSupport != null) {
    List<BannerListener> listeners=bannerSupport.getScrollStateChangedListenerById(cell.id);
    if (listeners != null) {
      for (int i=0; i < listeners.size(); i++) {
        BannerListener listener=listeners.get(i);
        listener.onPageScrollStateChanged(state);
      }
    }
  }
}
