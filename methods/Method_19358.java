@Override public void onRemoved(int position,int count){
  final List<Diff> dataHolders=new ArrayList<>(count);
  for (int i=0; i < count; i++) {
    mPlaceholders.remove(position);
    final Diff dataHolder=mDataHolders.remove(position);
    dataHolders.add(dataHolder);
  }
  mOperations.add(new Operation(Operation.DELETE,position,count,null,dataHolders));
}
