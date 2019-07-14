public static long[] arrayDifference(long[] arr,long[] subset){
  long[] res=new long[arr.length - subset.length];
  int pos=0;
  for (  long anArr : arr) {
    if (!Longs.contains(subset,anArr)) {
      res[pos]=anArr;
      pos++;
    }
  }
  assert pos == res.length;
  return res;
}
