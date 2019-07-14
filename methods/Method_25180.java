private static TransferResult<Nullness,AccessPathStore<Nullness>> noStoreChanges(Nullness value,TransferInput<?,AccessPathStore<Nullness>> input){
  return new RegularTransferResult<>(value,input.getRegularStore());
}
