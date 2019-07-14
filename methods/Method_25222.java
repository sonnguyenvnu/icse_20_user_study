@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitParameterizedType(ParameterizedTypeNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  Nullness value=visitParameterizedType();
  return noStoreChanges(value,input);
}
