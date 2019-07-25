@Override public int rank(short lowbits){
  int answer=Util.unsignedBinarySearch(content,0,cardinality,lowbits);
  if (answer >= 0) {
    return answer + 1;
  }
 else {
    return -answer - 1;
  }
}
