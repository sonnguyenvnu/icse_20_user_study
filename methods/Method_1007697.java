@Override public BaseBlock apply(BlockVector3 position){
  BlockState oldBlock=getExtent().getBlock(position);
  BlockState newBlock=blockState;
  for (  Entry<Property<?>,Object> entry : oldBlock.getStates().entrySet()) {
    @SuppressWarnings("unchecked") Property<Object> prop=(Property<Object>)entry.getKey();
    newBlock=newBlock.with(prop,entry.getValue());
  }
  return newBlock.toBaseBlock();
}
