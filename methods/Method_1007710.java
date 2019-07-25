public static void register(BlockState blockState){
  OptionalInt id=getBlockStateId(blockState);
  if (id.isPresent()) {
    int i=id.getAsInt();
    if (i >= blockStates.length) {
      int curLength=blockStates.length;
      do {
        curLength+=curLength >> 1;
      }
 while (i >= curLength);
      blockStates=Arrays.copyOf(blockStates,curLength);
    }
    BlockState existing=blockStates[i];
    checkState(existing == null || existing == blockState,"BlockState %s is using the same block ID (%s) as BlockState %s",blockState,i,existing);
    blockStates[i]=blockState;
  }
}
