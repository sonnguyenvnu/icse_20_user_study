private void lockedOn(ExpectedValueCheckingStore store){
  final Map<KeyColumn,StaticBuffer> m=expectedValuesByStore.computeIfAbsent(store,k -> new HashMap<>());
}
