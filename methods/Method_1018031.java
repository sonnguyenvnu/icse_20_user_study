@Override public long[] call(long[] countsA,long[] countsB) throws Exception {
  long[] countsResult=new long[countsA.length];
  for (int idx=0; idx < countsA.length; idx++) {
    countsResult[idx]=countsA[idx] + countsB[idx];
  }
  return countsResult;
}
