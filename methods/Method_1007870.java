public static BlockType adapt(Block block){
  return BlockTypes.get(ForgeRegistries.BLOCKS.getKey(block).toString());
}
