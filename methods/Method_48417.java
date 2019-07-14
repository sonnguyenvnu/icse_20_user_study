public long[] getLongs(int position,int length){
  long[] result=new long[length];
  for (int i=0; i < length; i++) {
    result[i]=getLong(position);
    position+=LONG_LEN;
  }
  return result;
}
