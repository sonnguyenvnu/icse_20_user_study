public static long[] insertSortedInc(long[] arr,long element){
  assert arr == null || isSortedInc(arr);
  long[] newArray=new long[(arr != null ? arr.length + 1 : 1)];
  int offset=0;
  if (arr != null) {
    for (int i=0; i < arr.length; i++) {
      Preconditions.checkArgument(element != arr[i]);
      if (element < arr[i]) {
        newArray[i]=element;
        offset=1;
      }
      newArray[i + offset]=arr[i];
    }
  }
  if (offset == 0)   newArray[newArray.length - 1]=element;
  return newArray;
}
