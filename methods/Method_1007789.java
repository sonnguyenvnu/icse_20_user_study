/** 
 * Generates the shape.
 * @param editSession The EditSession to use.
 * @param pattern The pattern to generate default materials from.
 * @param hollow Specifies whether to generate a hollow shape.
 * @return number of affected blocks.
 * @throws MaxChangedBlocksException
 */
public int generate(EditSession editSession,Pattern pattern,boolean hollow) throws MaxChangedBlocksException {
  int affected=0;
  for (  BlockVector3 position : getExtent()) {
    int x=position.getBlockX();
    int y=position.getBlockY();
    int z=position.getBlockZ();
    if (!hollow) {
      BaseBlock material=getMaterial(x,y,z,pattern.apply(position));
      if (material != null && editSession.setBlock(position,material)) {
        ++affected;
      }
      continue;
    }
    BaseBlock material=getMaterial(x,y,z,pattern.apply(position));
    if (material == null) {
      final int index=(y - cacheOffsetY) + (z - cacheOffsetZ) * cacheSizeY + (x - cacheOffsetX) * cacheSizeY * cacheSizeZ;
      cache[index]=-1;
      continue;
    }
    boolean draw=false;
    do {
      if (!isInsideCached(x + 1,y,z,pattern)) {
        draw=true;
        break;
      }
      if (!isInsideCached(x - 1,y,z,pattern)) {
        draw=true;
        break;
      }
      if (!isInsideCached(x,y,z + 1,pattern)) {
        draw=true;
        break;
      }
      if (!isInsideCached(x,y,z - 1,pattern)) {
        draw=true;
        break;
      }
      if (!isInsideCached(x,y + 1,z,pattern)) {
        draw=true;
        break;
      }
      if (!isInsideCached(x,y - 1,z,pattern)) {
        draw=true;
        break;
      }
    }
 while (false);
    if (!draw) {
      continue;
    }
    if (editSession.setBlock(position,material)) {
      ++affected;
    }
  }
  return affected;
}
