/** 
 * Checks whether the BlockStateHolder is contained within this category.
 * @param blockStateHolder The blockstateholder
 * @return If it's a part of this category
 */
public <B extends BlockStateHolder<B>>boolean contains(B blockStateHolder){
  return this.getAll().contains(blockStateHolder.getBlockType());
}
