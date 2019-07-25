/** 
 * Try to visit the given 'to' location.
 * @param from the origin block
 * @param to the block under question
 */
private void visit(BlockVector3 from,BlockVector3 to){
  BlockVector3 blockVector=to;
  if (!visited.contains(blockVector)) {
    visited.add(blockVector);
    if (isVisitable(from,to)) {
      queue.add(blockVector);
    }
  }
}
