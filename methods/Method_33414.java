protected void starting(){
  if (start == null) {
    oldCache=region.get().isCache();
    oldCacheHint=region.get().getCacheHint();
    radii=region.get().getBackground() == null ? null : region.get().getBackground().getFills().get(0).getRadii();
    insets=region.get().getBackground() == null ? null : region.get().getBackground().getFills().get(0).getInsets();
    start=fromValue.get();
    end=toValue.get();
    region.get().setCache(true);
    region.get().setCacheHint(CacheHint.SPEED);
  }
}
