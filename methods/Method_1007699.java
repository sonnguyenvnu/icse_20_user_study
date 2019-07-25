/** 
 * Add the given location to the list of locations to visit, provided that it has not been visited. The position passed to this method will still be visited even if it fails {@link #isVisitable(BlockVector3,BlockVector3)}. <p>This method should be used before the search begins, because if the position <em>does</em> fail the test, and the search has already visited it (because it is connected to another root point), the search will mark the position as "visited" and a call to this method will do nothing.</p>
 * @param position the position
 */
public void visit(BlockVector3 position){
  BlockVector3 blockVector=position;
  if (!visited.contains(blockVector)) {
    queue.add(blockVector);
    visited.add(blockVector);
  }
}
