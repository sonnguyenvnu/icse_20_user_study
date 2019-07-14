@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitNarrowingConversion(NarrowingConversionNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness result=visitNarrowingConversion();
  return noStoreChanges(result,input);
}
