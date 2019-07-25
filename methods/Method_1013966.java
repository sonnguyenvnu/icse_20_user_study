@Override @NonNullByDefault({}) public Stream<DiscoveryResult> stream(){
  final Storage<DiscoveryResult> discoveryResultStorage=this.discoveryResultStorage;
  if (discoveryResultStorage == null) {
    final ScheduledFuture<?> timeToLiveChecker=this.timeToLiveChecker;
    logger.error("The OSGi lifecycle has been violated (storage: {}, ttl checker cancelled: {}).",this.discoveryResultStorage == null ? "null" : this.discoveryResultStorage,timeToLiveChecker == null ? "null" : timeToLiveChecker.isCancelled());
    return Stream.empty();
  }
  final Collection<@Nullable DiscoveryResult> values=discoveryResultStorage.getValues();
  if (values == null) {
    logger.warn("The storage service violates the nullness requirements (get values must not return null) (storage class: {}).",discoveryResultStorage.getClass());
    return Stream.empty();
  }
  return values.stream().filter(Objects::nonNull);
}
