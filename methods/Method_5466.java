/** 
 * Advances  {@link #readAllocationNode} to the specified absolute position.
 * @param absolutePosition The position to which {@link #readAllocationNode} should be advanced.
 */
private void advanceReadTo(long absolutePosition){
  while (absolutePosition >= readAllocationNode.endPosition) {
    readAllocationNode=readAllocationNode.next;
  }
}
