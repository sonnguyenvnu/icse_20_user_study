/** 
 * ????
 * @param mdagDataArray
 * @param node
 * @return
 */
private int binarySearch(SimpleMDAGNode[] mdagDataArray,char node){
  if (transitionSetSize < 1) {
    return -1;
  }
  int high=transitionSetBeginIndex + transitionSetSize - 1;
  int low=transitionSetBeginIndex;
  while (low <= high) {
    int mid=((low + high) >>> 1);
    int cmp=mdagDataArray[mid].getLetter() - node;
    if (cmp < 0)     low=mid + 1;
 else     if (cmp > 0)     high=mid - 1;
 else     return mid;
  }
  return -1;
}
