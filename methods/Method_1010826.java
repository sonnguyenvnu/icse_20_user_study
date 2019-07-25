public int search(int index){
  int pointer=Arrays.binarySearch(indexes,index);
  if (pointer >= 0) {
    return pointer;
  }
 else {
    return pointer - 1;
  }
}
