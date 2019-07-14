static String badPositionIndexes(int start,int end,int size){
  if (start < 0 || start > size) {
    return badPositionIndex(start,size,"start index");
  }
  if (end < 0 || end > size) {
    return badPositionIndex(end,size,"end index");
  }
  return String.format("end index (%s) must not be less than start index (%s)",end,start);
}
