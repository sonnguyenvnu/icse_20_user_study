public short[] getShorts(int length){
  short[] result=super.getShorts(position,length);
  position+=length * SHORT_LEN;
  return result;
}
