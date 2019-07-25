@Override public void publish(@NotNull final Set<String> changedKeys){
  checkNotNull(changedKeys,"changedKeys == null");
  if (changedKeys.isEmpty()) {
    return;
  }
  Set<RecordChangeSubscriber> iterableSubscribers;
synchronized (this) {
    iterableSubscribers=new LinkedHashSet<>(subscribers);
  }
  for (  RecordChangeSubscriber subscriber : iterableSubscribers) {
    subscriber.onCacheRecordsChanged(changedKeys);
  }
}
