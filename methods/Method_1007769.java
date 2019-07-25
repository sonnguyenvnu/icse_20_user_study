/** 
 * Apply a raw heightmap to the region
 * @param data the data
 * @return number of blocks affected
 * @throws MaxChangedBlocksException
 */
public int apply(int[] data) throws MaxChangedBlocksException {
  checkNotNull(data);
  BlockVector3 minY=region.getMinimumPoint();
  int originX=minY.getBlockX();
  int originY=minY.getBlockY();
  int originZ=minY.getBlockZ();
  int maxY=region.getMaximumPoint().getBlockY();
  BlockState fillerAir=BlockTypes.AIR.getDefaultState();
  int blocksChanged=0;
  for (int z=0; z < height; ++z) {
    for (int x=0; x < width; ++x) {
      int index=z * width + x;
      int curHeight=this.data[index];
      int newHeight=Math.min(maxY,data[index]);
      int xr=x + originX;
      int zr=z + originZ;
      double scale=(double)(curHeight - originY) / (double)(newHeight - originY);
      if (newHeight > curHeight) {
        BlockState existing=session.getBlock(BlockVector3.at(xr,curHeight,zr));
        if (existing.getBlockType() != BlockTypes.WATER && existing.getBlockType() != BlockTypes.LAVA) {
          session.setBlock(BlockVector3.at(xr,newHeight,zr),existing);
          ++blocksChanged;
          for (int y=newHeight - 1 - originY; y >= 0; --y) {
            int copyFrom=(int)(y * scale);
            session.setBlock(BlockVector3.at(xr,originY + y,zr),session.getBlock(BlockVector3.at(xr,originY + copyFrom,zr)));
            ++blocksChanged;
          }
        }
      }
 else       if (curHeight > newHeight) {
        for (int y=0; y < newHeight - originY; ++y) {
          int copyFrom=(int)(y * scale);
          session.setBlock(BlockVector3.at(xr,originY + y,zr),session.getBlock(BlockVector3.at(xr,originY + copyFrom,zr)));
          ++blocksChanged;
        }
        session.setBlock(BlockVector3.at(xr,newHeight,zr),session.getBlock(BlockVector3.at(xr,curHeight,zr)));
        ++blocksChanged;
        for (int y=newHeight + 1; y <= curHeight; ++y) {
          session.setBlock(BlockVector3.at(xr,y,zr),fillerAir);
          ++blocksChanged;
        }
      }
    }
  }
  return blocksChanged;
}
