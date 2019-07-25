/** 
 * Create a WorldEdit location from a Sponge location.
 * @param location the Sponge location
 * @return a WorldEdit location
 */
public static Location adapt(org.spongepowered.api.world.Location<org.spongepowered.api.world.World> location,Vector3d rotation){
  checkNotNull(location);
  Vector3 position=asVector(location);
  return new Location(adapt(location.getExtent()),position,(float)rotation.getX(),(float)rotation.getY());
}
