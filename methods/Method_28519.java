private int getLastVisibleItem(int[] lastVisibleItemPositions){
  int maxSize=0;
  for (int i=0; i < lastVisibleItemPositions.length; i++) {
    if (i == 0) {
      maxSize=lastVisibleItemPositions[i];
    }
 else     if (lastVisibleItemPositions[i] > maxSize) {
      maxSize=lastVisibleItemPositions[i];
    }
  }
  return maxSize;
}
