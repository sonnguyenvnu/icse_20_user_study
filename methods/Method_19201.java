@Override public int calculateDtToFit(int viewStart,int viewEnd,int boxStart,int boxEnd,int snapPreference){
  final int result=(boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
  return result + mOffset;
}
