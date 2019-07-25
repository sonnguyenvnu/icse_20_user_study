/** 
 * Create a WorldEdit world from a Sponge world.
 * @param world the Sponge world
 * @return a WorldEdit world
 */
public static World adapt(org.spongepowered.api.world.World world){
  checkNotNull(world);
  return SpongeWorldEdit.inst().getWorld(world);
}
