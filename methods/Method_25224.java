@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitMarker(MarkerNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  ReadableUpdates updates=new ReadableUpdates();
  Nullness result=visitMarker(node,values(input),updates);
  return updateRegularStore(result,input,updates);
}
