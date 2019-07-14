/** 
 * Called after writing sample data. May cause  {@link #writeAllocationNode} to be advanced.
 * @param length The number of bytes that were written.
 */
private void postAppend(int length){
  totalBytesWritten+=length;
  if (totalBytesWritten == writeAllocationNode.endPosition) {
    writeAllocationNode=writeAllocationNode.next;
  }
}
