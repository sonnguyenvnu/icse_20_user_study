@Override public void onScrolled(RecyclerView rv,int dx,int dy){
  final int scrollRange=rv.computeVerticalScrollRange();
  final int scrollExtent=rv.computeVerticalScrollExtent();
  scrollOffsetRange=scrollRange - scrollExtent;
  handleOffsetRange=scrollExtent - dpToPixel(rv.getContext(),2 * HANDLE_VERTICAL_MARGIN + HANDLE_SIZE_DP);
  scrollOffset=rv.computeVerticalScrollOffset();
  if (userControlling) {
    return;
  }
  final float scale=((float)scrollOffset) / scrollOffsetRange;
  handleOffset=(int)(handleOffsetRange * scale);
  handleOffsetDV.set((float)handleOffset);
}
