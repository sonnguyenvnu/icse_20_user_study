/** 
 * Sets the blocks at the center of the given region to the given pattern. If the center sits between two blocks on a certain axis, then two blocks will be placed to mark the center.
 * @param region the region to find the center of
 * @param pattern the replacement pattern
 * @return the number of blocks placed
 * @throws MaxChangedBlocksException thrown if too many blocks are changed
 */
public int center(Region region,Pattern pattern) throws MaxChangedBlocksException {
  checkNotNull(region);
  checkNotNull(pattern);
  Vector3 center=region.getCenter();
  Region centerRegion=new CuboidRegion(getWorld(),BlockVector3.at(((int)center.getX()),((int)center.getY()),((int)center.getZ())),BlockVector3.at(MathUtils.roundHalfUp(center.getX()),center.getY(),MathUtils.roundHalfUp(center.getZ())));
  return setBlocks(centerRegion,pattern);
}
