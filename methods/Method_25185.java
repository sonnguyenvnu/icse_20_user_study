@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitNullChk(NullChkNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitNullChk();
  return noStoreChanges(value,input);
}
