@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitNumericalMinus(NumericalMinusNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitNumericalMinus();
  return noStoreChanges(value,input);
}
