@Override public Iterable<String> getKeys(final String userPrefix){
  return StreamSupport.stream(config.spliterator(),false).map(Entry::getKey).filter(internalKey -> {
    String k=internalKey;
    if (null != prefix) {
      if (k.startsWith(prefix)) {
        k=getUserKey(k);
      }
 else {
        return false;
      }
    }
    return k.startsWith(userPrefix);
  }
).map(internalKey -> {
    String userKey=getUserKey(internalKey);
    Preconditions.checkState(userKey.startsWith(userPrefix));
    return userKey;
  }
).collect(Collectors.toList());
}
