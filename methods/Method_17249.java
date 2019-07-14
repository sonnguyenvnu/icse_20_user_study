@Override public void notifyRemoval(@Nullable K key,@Nullable V value,RemovalCause cause){
  requireNonNull(removalListener(),"Notification should be guarded with a check");
  executor.execute(() -> removalListener().onRemoval(key,value,cause));
}
