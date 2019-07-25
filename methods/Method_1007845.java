public static net.minecraft.block.BlockState adapt(BlockState blockState){
  Block mcBlock=adapt(blockState.getBlockType());
  net.minecraft.block.BlockState newState=mcBlock.getDefaultState();
  Map<Property<?>,Object> states=blockState.getStates();
  return applyProperties(mcBlock.getStateFactory(),newState,states);
}
