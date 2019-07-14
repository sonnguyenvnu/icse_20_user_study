public short[] getShorts(int position,int length){
  short[] result=new short[length];
  for (int i=0; i < length; i++) {
    result[i]=getShort(position);
    position+=SHORT_LEN;
  }
  return result;
}
