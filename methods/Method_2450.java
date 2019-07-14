public static Long computeMinimumDistance(Long[] arrayA,Long[] arrayB){
  int aIndex=0;
  int bIndex=0;
  long min=Math.abs(arrayA[0] - arrayB[0]);
  while (true) {
    if (arrayA[aIndex] > arrayB[bIndex]) {
      bIndex++;
    }
 else {
      aIndex++;
    }
    if (aIndex >= arrayA.length || bIndex >= arrayB.length) {
      break;
    }
    if (Math.abs(arrayA[aIndex] - arrayB[bIndex]) < min) {
      min=Math.abs(arrayA[aIndex] - arrayB[bIndex]);
    }
  }
  return min;
}
