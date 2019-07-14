private void growKeyAndValueArrays(int minNeededSize){
  int n=ArrayUtils.idealLongArraySize(minNeededSize);
  int[] nkeys=new int[n];
  long[] nvalues=new long[n];
  System.arraycopy(mKeys,0,nkeys,0,mKeys.length);
  System.arraycopy(mValues,0,nvalues,0,mValues.length);
  mKeys=nkeys;
  mValues=nvalues;
}
