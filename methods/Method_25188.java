@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitNumericalSubtraction(NumericalSubtractionNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitNumericalSubtraction();
  return noStoreChanges(value,input);
}
