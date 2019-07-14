@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitWideningConversion(WideningConversionNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitWideningConversion();
  return noStoreChanges(value,input);
}
