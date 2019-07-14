@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitNumericalAddition(NumericalAdditionNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness result=visitNumericalAddition();
  return noStoreChanges(result,input);
}
