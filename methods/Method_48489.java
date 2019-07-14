public int[] getAllContainedIDs(){
  int[] result;
  if (lowerID < upperID) {
    result=new int[upperID - lowerID];
    int pos=0;
    for (int id=lowerID; id < upperID; id++) {
      result[pos++]=id;
    }
  }
 else {
    result=new int[(idUpperBound - lowerID) + (upperID)];
    int pos=0;
    for (int id=0; id < upperID; id++) {
      result[pos++]=id;
    }
    for (int id=lowerID; id < idUpperBound; id++) {
      result[pos++]=id;
    }
  }
  return result;
}
