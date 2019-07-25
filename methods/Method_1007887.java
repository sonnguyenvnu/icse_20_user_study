/** 
 * Create a Sponge world from a WorldEdit world.
 * @param world the WorldEdit world
 * @return a Sponge world
 */
public static org.spongepowered.api.world.World adapt(World world){
  checkNotNull(world);
  if (world instanceof SpongeWorld) {
    return ((SpongeWorld)world).getWorld();
  }
 else {
    org.spongepowered.api.world.World match=Sponge.getServer().getWorld(world.getName()).orElse(null);
    if (match != null) {
      return match;
    }
 else {
      throw new IllegalArgumentException("Can't find a Sponge world for " + world);
    }
  }
}
