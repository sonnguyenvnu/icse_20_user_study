@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitIntegerRemainder(IntegerRemainderNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitIntegerRemainder();
  return noStoreChanges(value,input);
}
