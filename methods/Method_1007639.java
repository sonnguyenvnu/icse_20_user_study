/** 
 * Helper method.
 * @param world the world that contains the tree
 * @param origin any point contained in the floating tree
 * @return a set containing all blocks in the tree/shroom or null if this is not a floating tree/shroom.
 */
private Set<BlockVector3> bfs(World world,BlockVector3 origin){
  final Set<BlockVector3> visited=new HashSet<>();
  final LinkedList<BlockVector3> queue=new LinkedList<>();
  queue.addLast(origin);
  visited.add(origin);
  while (!queue.isEmpty()) {
    final BlockVector3 current=queue.removeFirst();
    for (    BlockVector3 recurseDirection : recurseDirections) {
      final BlockVector3 next=current.add(recurseDirection);
      if (origin.distanceSq(next) > rangeSq) {
        continue;
      }
      if (visited.add(next)) {
        BlockState state=world.getBlock(next);
        if (state.getBlockType().getMaterial().isAir() || state.getBlockType() == BlockTypes.SNOW) {
          continue;
        }
        if (isTreeBlock(state.getBlockType())) {
          queue.addLast(next);
        }
 else {
          final BlockType currentType=world.getBlock(current).getBlockType();
          if (!BlockCategories.LEAVES.contains(currentType) && currentType != BlockTypes.VINE) {
            return null;
          }
        }
      }
    }
  }
  return visited;
}
