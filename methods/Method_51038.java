public int lineNumberFromOffset(int offset){
  int search=Arrays.binarySearch(lineOffsets,offset);
  int lineNumber;
  if (search >= 0) {
    lineNumber=search;
  }
 else {
    int insertionPoint=search;
    insertionPoint+=1;
    insertionPoint*=-1;
    lineNumber=insertionPoint - 1;
  }
  return lineNumber + 1;
}
