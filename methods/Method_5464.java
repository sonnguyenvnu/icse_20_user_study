/** 
 * Reads data from the front of the rolling buffer.
 * @param absolutePosition The absolute position from which data should be read.
 * @param target The buffer into which data should be written.
 * @param length The number of bytes to read.
 */
private void readData(long absolutePosition,ByteBuffer target,int length){
  advanceReadTo(absolutePosition);
  int remaining=length;
  while (remaining > 0) {
    int toCopy=Math.min(remaining,(int)(readAllocationNode.endPosition - absolutePosition));
    Allocation allocation=readAllocationNode.allocation;
    target.put(allocation.data,readAllocationNode.translateOffset(absolutePosition),toCopy);
    remaining-=toCopy;
    absolutePosition+=toCopy;
    if (absolutePosition == readAllocationNode.endPosition) {
      readAllocationNode=readAllocationNode.next;
    }
  }
}
