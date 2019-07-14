private void checkSingleExpectedValueUnsafe(final KeyColumn kc,final StaticBuffer ev,final ExpectedValueCheckingStore store) throws BackendException {
  final StaticBuffer nextBuf=BufferUtil.nextBiggerBuffer(kc.getColumn());
  KeySliceQuery ksq=new KeySliceQuery(kc.getKey(),kc.getColumn(),nextBuf);
  Iterable<Entry> actualEntries=store.getBackingStore().getSlice(ksq,strongConsistentTx);
  if (null == actualEntries)   actualEntries=ImmutableList.of();
  actualEntries=Iterables.filter(actualEntries,input -> {
    if (!input.getColumn().equals(kc.getColumn())) {
      log.debug("Dropping entry {} (only accepting column {})",input,kc.getColumn());
      return false;
    }
    log.debug("Accepting entry {}",input);
    return true;
  }
);
  final Iterable<StaticBuffer> actualValues=Iterables.transform(actualEntries,e -> {
    final StaticBuffer actualCol=e.getColumnAs(StaticBuffer.STATIC_FACTORY);
    assert null != actualCol;
    assert null != kc.getColumn();
    assert 0 >= kc.getColumn().compareTo(actualCol);
    assert 0 > actualCol.compareTo(nextBuf);
    return e.getValueAs(StaticBuffer.STATIC_FACTORY);
  }
);
  final Iterable<StaticBuffer> expectedValues;
  if (null == ev) {
    expectedValues=ImmutableList.of();
  }
 else {
    expectedValues=ImmutableList.of(ev);
  }
  if (!Iterables.elementsEqual(expectedValues,actualValues)) {
    throw new PermanentLockingException("Expected value mismatch for " + kc + ": expected=" + expectedValues + " vs actual=" + actualValues + " (store=" + store.getName() + ")");
  }
}
