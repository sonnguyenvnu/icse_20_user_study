public void unregister(OnTickListener onTickListener){
  Disposable disposable=tickCache.get(onTickListener);
  if (disposable != null) {
    disposable.dispose();
    tickCache.remove(onTickListener);
  }
}
