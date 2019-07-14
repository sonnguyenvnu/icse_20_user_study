@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitAssertionError(AssertionErrorNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitAssertionError();
  return noStoreChanges(value,input);
}
