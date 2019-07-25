@Override public boolean test(BlockVector3 vector){
  BlockState block=getExtent().getBlock(vector);
  final Map<Property<Object>,Object> checkProps=cache.computeIfAbsent(block.getBlockType(),(b -> Blocks.resolveProperties(states,b)));
  if (strict && checkProps.isEmpty()) {
    return false;
  }
  return checkProps.entrySet().stream().allMatch(entry -> block.getState(entry.getKey()) == entry.getValue());
}
