public float[] getFloats(int position,int length){
  float[] result=new float[length];
  for (int i=0; i < length; i++) {
    result[i]=getFloat(position);
    position+=FLOAT_LEN;
  }
  return result;
}
