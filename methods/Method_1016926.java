@Override public int location(int index){
  if (indices == null) {
    return index;
  }
 else {
    return Arrays.binarySearch(indices,index);
  }
}
