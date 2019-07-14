public FloatList getSubset(int start,int num){
  float[] subset=new float[num];
  System.arraycopy(data,start,subset,0,num);
  return new FloatList(subset);
}
