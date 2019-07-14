static protected int expandArraySize(int currSize,int newMinSize){
  int newSize=currSize;
  while (newSize < newMinSize) {
    newSize<<=1;
  }
  return newSize;
}
