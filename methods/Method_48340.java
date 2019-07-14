@Override public synchronized KeyColumnValueStore openDatabase(String name) throws BackendException {
  if (stores.containsKey(name))   return stores.get(name);
  KeyColumnValueStore store=manager.openDatabase(name);
  final String lockerName=store.getName() + lockStoreSuffix;
  ExpectedValueCheckingStore wrappedStore=new ExpectedValueCheckingStore(store,lockerProvider.getLocker(lockerName));
  stores.put(name,wrappedStore);
  return wrappedStore;
}
