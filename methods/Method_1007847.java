public static Block adapt(BlockType blockType){
  return Registry.BLOCK.get(new Identifier(blockType.getId()));
}
