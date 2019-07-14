public void resize(int length){
  if (length > data.length) {
    double[] temp=new double[length];
    System.arraycopy(data,0,temp,0,count);
    data=temp;
  }
 else   if (length > count) {
    Arrays.fill(data,count,length,0);
  }
  count=length;
}
