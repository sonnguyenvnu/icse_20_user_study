public int[] getInts(int position,int length){
  int[] result=new int[length];
  for (int i=0; i < length; i++) {
    result[i]=getInt(position);
    position+=INT_LEN;
  }
  return result;
}
