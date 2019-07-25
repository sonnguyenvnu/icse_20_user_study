public static BlockState adapt(net.minecraft.block.BlockState blockState){
  BlockType blockType=adapt(blockState.getBlock());
  return blockType.getState(adaptProperties(blockType,blockState.getValues()));
}
