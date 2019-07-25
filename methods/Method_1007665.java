/** 
 * Make dirt green.
 * @param position a position
 * @param radius a radius
 * @param onlyNormalDirt only affect normal dirt (data value 0)
 * @return number of blocks affected
 * @throws MaxChangedBlocksException thrown if too many blocks are changed
 */
public int green(BlockVector3 position,double radius,boolean onlyNormalDirt) throws MaxChangedBlocksException {
  int affected=0;
  final double radiusSq=radius * radius;
  final int ox=position.getBlockX();
  final int oy=position.getBlockY();
  final int oz=position.getBlockZ();
  final BlockState grass=BlockTypes.GRASS_BLOCK.getDefaultState();
  final int ceilRadius=(int)Math.ceil(radius);
  for (int x=ox - ceilRadius; x <= ox + ceilRadius; ++x) {
    for (int z=oz - ceilRadius; z <= oz + ceilRadius; ++z) {
      if ((BlockVector3.at(x,oy,z)).distanceSq(position) > radiusSq) {
        continue;
      }
      for (int y=world.getMaxY(); y >= 1; --y) {
        final BlockVector3 pt=BlockVector3.at(x,y,z);
        final BlockState block=getBlock(pt);
        if (block.getBlockType() == BlockTypes.DIRT || (!onlyNormalDirt && block.getBlockType() == BlockTypes.COARSE_DIRT)) {
          if (setBlock(pt,grass)) {
            ++affected;
          }
          break;
        }
 else         if (block.getBlockType() == BlockTypes.WATER || block.getBlockType() == BlockTypes.LAVA) {
          break;
        }
 else         if (block.getBlockType().getMaterial().isMovementBlocker()) {
          break;
        }
      }
    }
  }
  return affected;
}
