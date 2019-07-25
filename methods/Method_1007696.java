@Override public BaseBlock apply(BlockVector3 position){
  BlockState block=getExtent().getBlock(position);
  for (  Entry<Property<Object>,Object> entry : cache.computeIfAbsent(block.getBlockType(),(b -> resolveProperties(states,b))).entrySet()) {
    block=block.with(entry.getKey(),entry.getValue());
  }
  return block.toBaseBlock();
}
