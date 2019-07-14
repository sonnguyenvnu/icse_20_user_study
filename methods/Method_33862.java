private int findMin(int[] firstPositions){
  int min=firstPositions[0];
  for (  int value : firstPositions) {
    if (value < min) {
      min=value;
    }
  }
  return min;
}
