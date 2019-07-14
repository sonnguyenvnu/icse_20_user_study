@Override public int calculateDtToFit(int viewStart,int viewEnd,int boxStart,int boxEnd,int snapPreference){
  int result=super.calculateDtToFit(viewStart,viewEnd,boxStart,boxEnd,snapPreference);
  return result + mOffset;
}
