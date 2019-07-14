@Override public final TransferResult<Nullness,AccessPathStore<Nullness>> visitAssignment(AssignmentNode node,TransferInput<Nullness,AccessPathStore<Nullness>> input){
  ReadableUpdates updates=new ReadableUpdates();
  Nullness result=visitAssignment(node,values(input),updates);
  return updateRegularStore(result,input,updates);
}
