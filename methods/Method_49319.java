public static boolean isSortedInc(long[] arr){
  for (int i=0; i < arr.length; i++) {
    if (i > 0 && arr[i] <= arr[i - 1])     return false;
  }
  return true;
}
