/** 
 * Create a Bukkit BlockData from a WorldEdit BlockStateHolder
 * @param block The WorldEdit BlockStateHolder
 * @return The Bukkit BlockData
 */
public static <B extends BlockStateHolder<B>>BlockData adapt(B block){
  checkNotNull(block);
  return blockDataCache.computeIfAbsent(block.getAsString(),new Function<String,BlockData>(){
    @Nullable @Override public BlockData apply(    @Nullable String input){
      return Bukkit.createBlockData(block.getAsString());
    }
  }
).clone();
}
