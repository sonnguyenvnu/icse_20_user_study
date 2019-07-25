public static BlockType adapt(Block block){
  return BlockTypes.get(Registry.BLOCK.getId(block).toString());
}
