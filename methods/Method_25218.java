@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitPrimitiveType(PrimitiveTypeNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitPrimitiveType();
  return noStoreChanges(value,input);
}
