@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitNumericalPlus(NumericalPlusNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitNumericalPlus();
  return noStoreChanges(value,input);
}
