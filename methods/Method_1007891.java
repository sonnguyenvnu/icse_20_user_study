/** 
 * Create a Sponge location from a WorldEdit location.
 * @param location the WorldEdit location
 * @return a Sponge location
 */
public static org.spongepowered.api.world.Location<org.spongepowered.api.world.World> adapt(Location location){
  checkNotNull(location);
  Vector3 position=location.toVector();
  return new org.spongepowered.api.world.Location<>(adapt((World)location.getExtent()),position.getX(),position.getY(),position.getZ());
}
