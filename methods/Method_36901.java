public boolean isRegistered(@NonNull OnTickListener onTickListener){
  return tickCache.containsKey(onTickListener);
}
