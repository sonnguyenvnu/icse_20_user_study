@Override public boolean isRegistered(OnTickListener onTickListener){
  return mListeners.containsKey(onTickListener);
}
