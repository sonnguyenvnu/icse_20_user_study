@Override public void viewport(int x,int y,int w,int h){
  float scale=getPixelScale();
  viewportImpl((int)scale * x,(int)(scale * y),(int)(scale * w),(int)(scale * h));
}
