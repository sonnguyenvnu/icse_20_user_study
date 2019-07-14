/** 
 * Reads data from the front of the rolling buffer.
 * @param absolutePosition The absolute position from which data should be read.
 * @param target The array into which data should be written.
 * @param length The number of bytes to read.
 */
private void readData(long absolutePosition,byte[] target,int length){
  advanceReadTo(absolutePosition);
  int remaining=length;
  while (remaining > 0) {
    int toCopy=Math.min(remaining,(int)(readAllocationNode.endPosition - absolutePosition));
    Allocation allocation=readAllocationNode.allocation;
    System.arraycopy(allocation.data,readAllocationNode.translateOffset(absolutePosition),target,length - remaining,toCopy);
    remaining-=toCopy;
    absolutePosition+=toCopy;
    if (absolutePosition == readAllocationNode.endPosition) {
      readAllocationNode=readAllocationNode.next;
    }
  }
}
