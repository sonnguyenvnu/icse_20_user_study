/** 
 * Calculates the size of the packet starting from  {@code startSegmentIndex}.
 * @param startSegmentIndex the index of the first segment of the packet.
 * @return Size of the packet.
 */
private int calculatePacketSize(int startSegmentIndex){
  segmentCount=0;
  int size=0;
  while (startSegmentIndex + segmentCount < pageHeader.pageSegmentCount) {
    int segmentLength=pageHeader.laces[startSegmentIndex + segmentCount++];
    size+=segmentLength;
    if (segmentLength != 255) {
      break;
    }
  }
  return size;
}
