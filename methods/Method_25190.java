@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitNumericalMultiplication(NumericalMultiplicationNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitNumericalMultiplication();
  return noStoreChanges(value,input);
}
