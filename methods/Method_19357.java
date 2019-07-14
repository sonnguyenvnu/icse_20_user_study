@Override public void onInserted(int position,int count){
  final List<ComponentContainer> placeholders=new ArrayList<>(count);
  final List<Diff> dataHolders=new ArrayList<>(count);
  for (int i=0; i < count; i++) {
    final int index=position + i;
    final ComponentContainer componentContainer=new ComponentContainer(null,true);
    mPlaceholders.add(index,componentContainer);
    placeholders.add(componentContainer);
    final Diff dataHolder=new Diff(null,null);
    mDataHolders.add(index,dataHolder);
    dataHolders.add(dataHolder);
  }
  mOperations.add(new Operation(Operation.INSERT,position,-1,placeholders,dataHolders));
}
