/** 
 * Add the given biomes to the list of criteria.
 * @param biomes a list of biomes
 */
public void add(Collection<BiomeType> biomes){
  checkNotNull(biomes);
  this.biomes.addAll(biomes);
}
