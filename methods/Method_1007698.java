@Override public BaseBlock apply(BlockVector3 position){
  BaseBlock block=getExtent().getFullBlock(position);
  @SuppressWarnings("unchecked") Property<Object> prop=(Property<Object>)block.getBlockType().getPropertyMap().getOrDefault("waterlogged",null);
  if (prop != null) {
    return block.with(prop,false);
  }
  return BlockTypes.AIR.getDefaultState().toBaseBlock();
}
