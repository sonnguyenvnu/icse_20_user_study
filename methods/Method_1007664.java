/** 
 * Thaw blocks in a radius.
 * @param position the position
 * @param radius the radius
 * @return number of blocks affected
 * @throws MaxChangedBlocksException thrown if too many blocks are changed
 */
public int thaw(BlockVector3 position,double radius) throws MaxChangedBlocksException {
  int affected=0;
  double radiusSq=radius * radius;
  int ox=position.getBlockX();
  int oy=position.getBlockY();
  int oz=position.getBlockZ();
  BlockState air=BlockTypes.AIR.getDefaultState();
  BlockState water=BlockTypes.WATER.getDefaultState();
  int ceilRadius=(int)Math.ceil(radius);
  for (int x=ox - ceilRadius; x <= ox + ceilRadius; ++x) {
    for (int z=oz - ceilRadius; z <= oz + ceilRadius; ++z) {
      if ((BlockVector3.at(x,oy,z)).distanceSq(position) > radiusSq) {
        continue;
      }
      for (int y=world.getMaxY(); y >= 1; --y) {
        BlockVector3 pt=BlockVector3.at(x,y,z);
        BlockType id=getBlock(pt).getBlockType();
        if (id == BlockTypes.ICE) {
          if (setBlock(pt,water)) {
            ++affected;
          }
        }
 else         if (id == BlockTypes.SNOW) {
          if (setBlock(pt,air)) {
            ++affected;
          }
        }
 else         if (id.getMaterial().isAir()) {
          continue;
        }
        break;
      }
    }
  }
  return affected;
}
