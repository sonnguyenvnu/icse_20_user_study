private static long[] ensureSpace(long[] data,int offset){
  if (offset + 1 <= data.length)   return data;
  final long[] newData=new long[Math.max(data.length * 2,offset + 1)];
  System.arraycopy(data,0,newData,0,offset);
  return newData;
}
