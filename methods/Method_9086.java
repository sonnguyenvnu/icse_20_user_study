private static boolean fits(int[] a,int value,int start,int end){
  if (end > a.length) {
    return false;
  }
  for (int i=start; i < end; i++) {
    if (a[i] > value) {
      return false;
    }
  }
  return true;
}
