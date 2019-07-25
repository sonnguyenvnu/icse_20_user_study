private void invalid(BlockId blockId){
  requestBlockIds.invalidate(blockId);
  fetchFlag=true;
}
