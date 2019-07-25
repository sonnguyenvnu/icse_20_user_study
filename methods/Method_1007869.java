public static Block adapt(BlockType blockType){
  return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockType.getId()));
}
