@Override public <V>Iterator<Property<V>> properties(final String... keyNames){
  verifyAccess();
  Stream<PropertyKey> keys;
  if (keyNames == null || keyNames.length == 0) {
    keys=IteratorUtils.stream(it().getPropertyKeysDirect().iterator());
  }
 else {
    keys=Stream.of(keyNames).map(s -> tx().getPropertyKey(s)).filter(rt -> rt != null && getValueDirect(rt) != null);
  }
  return keys.map(rt -> (Property<V>)new SimpleJanusGraphProperty<V>(this,rt,valueInternal(rt))).iterator();
}
