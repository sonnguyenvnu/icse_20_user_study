/** 
 * Create a WorldEdit BlockState from a Bukkit BlockData
 * @param blockData The Bukkit BlockData
 * @return The WorldEdit BlockState
 */
public static BlockState adapt(BlockData blockData){
  checkNotNull(blockData);
  return blockStateCache.computeIfAbsent(blockData.getAsString(),new Function<String,BlockState>(){
    @Nullable @Override public BlockState apply(    @Nullable String input){
      try {
        return WorldEdit.getInstance().getBlockFactory().parseFromInput(input,TO_BLOCK_CONTEXT).toImmutableState();
      }
 catch (      InputParseException e) {
        e.printStackTrace();
        return null;
      }
    }
  }
);
}
